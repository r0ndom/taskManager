package com.pb.task.manager.service;

import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.*;
import com.pb.task.manager.model.filter.TaskSearchFilter;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mednikov on 05.01.2016.
 */
@Service
public class ActivitiService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;
    @Autowired
    private UserDao userDao;

    private String startProcess() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("process");
        String processId = instance.getProcessInstanceId();
        TaskQuery query = taskService.createTaskQuery().processInstanceId(processId);
        Task task = query.singleResult();
        return task.getId();
    }

    public String submitForm(FormData formData) {
        String id = (formData.getId() == null) ? startProcess() : formData.getId();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        formService.submitTaskFormData(id, formData.getMap());
        return task.getExecutionId();
    }

    public TaskData getTaskData(String id) {
        Task task = taskService.createTaskQuery().executionId(id).singleResult();
        return generateTaskData(task);
    }


    private Map<String, Object> getParams(String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        Task task = taskService.createTaskQuery().executionId(id).singleResult();
        params.put("state", task.getFormKey());
        params.putAll(taskService.getVariables(task.getId()));
        return params;
    }

    public List<TaskData> findAll() {
        List<Task> query = taskService.createTaskQuery().list();
        return generateTaskDataList(query);
    }

    public List<TaskData> search(TaskSearchFilter filter) {
        List<Task> result = new ArrayList<Task>();
        List<Task> query = taskService.createTaskQuery().list();
        for (Task task : query) {
            Map<String, Object> params = getParams(task.getExecutionId());
            String status = params.get("status") != null ? getString(params.get("status")) : "new";
            String author = getString(params.get("author"));
            String executor = getString(params.get("executor"));
            if (status.equals(filter.getStatus())||filter.getStatus().equals("all")) {
                if (author.equals(filter.getAuthor())||filter.getAuthor().equals(""))
                    if(executor.equals(filter.getExecutor())||filter.getExecutor().equals(""))
                        result.add(task);
            }

        }
        return generateTaskDataList(result);
    }

    private List<TaskData> generateTaskDataList(List<Task> tasks) {
        List<TaskData> result = new ArrayList<TaskData>();
        for (Task task : tasks) {
            result.add(generateTaskData(task));
        }
        return result;
    }

    private TaskData generateTaskData(Task task) {
        Map<String, Object> params = getParams(task.getExecutionId());
        State state = State.getState(getString(params.get("state")));
        Status status = Status.getStatus(getString(params.get("status")));
        TaskData taskData = new TaskData();
        taskData.setActivitiDynamicId(task.getId());
        taskData.setId(task.getExecutionId());
        taskData.setState(state);
        taskData.setStatus(status != null ? status : Status.NEW);
        String authorLdap = getString(params.get("author"));
        taskData.setAuthor(userDao.findByLdap(authorLdap));
        taskData.setName(getString(params.get("name")));
        String executorLdap = getString(params.get("executor"));
        taskData.setExecutor(userDao.findByLdap(executorLdap));
        String expectedTimeStr = getString(params.get("expectedTime"));
        Long expectedTime = (expectedTimeStr != null && !expectedTimeStr.equals(""))
                ? Long.parseLong(expectedTimeStr) : null;
        taskData.setExpectedTime(expectedTime);
        taskData.setDescription(getString(params.get("description")));
        return taskData;
    }

    private String getString(Object object) {
        return object != null ? String.valueOf(object) : "";
    }

    public boolean checkUserAccess(String id) {
        User currentUser = userDao.getCurrentUser();
        Map<String, Object> params = getParams(id);
        String ldap = getString(params.get("executor"));
        if (ldap == null || ldap.equals("")) {
            return !currentUser.getLdap().equals(getString(params.get("author")));
        }

        User executor = userDao.findByLdap(ldap);
        return executor != null && currentUser.getLdap().equals(ldap);
    }
}
