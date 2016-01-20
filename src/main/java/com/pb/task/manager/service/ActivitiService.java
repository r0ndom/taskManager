package com.pb.task.manager.service;

import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.*;
import com.pb.task.manager.model.filter.TaskSearchFilter;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
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
    private ManagementService managementService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private UserDao userDao;

    public String submitForm(FormData formData) {
        String id = formData.getId();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        formService.submitTaskFormData(id, formData.getMap());
        return task.getExecutionId();
    }

    public String getTaskIdByExecutionId(String executionId) {
        Task task = taskService.createTaskQuery().executionId(executionId).singleResult();
        return task.getId();
    }


    public List<FormProperty> getFormProperty(String id) {
        System.out.println("getFormProperty by id: " + id);
        return formService.getTaskFormData(id).getFormProperties();
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

    public boolean checkUserAccess(String id) {
        User currentUser = userDao.getCurrentUser();
        Task task = taskService.createTaskQuery().taskId(id).singleResult();
        Map<String, Object> params = getParams(task.getExecutionId());
        String ldap = getString(params.get("executor"));
        if (ldap == null || ldap.equals("")) {
            return !currentUser.getLdap().equals(getString(params.get("author")));
        }

        User executor = userDao.findByLdap(ldap);
        return executor != null && currentUser.getLdap().equals(ldap);
    }

    private Map<String, Object> getParams(String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        Task task = taskService.createTaskQuery().executionId(id).singleResult();
        params.put("state", task.getFormKey());
        params.putAll(taskService.getVariables(task.getId()));
        return params;
    }

    public String startProcess() {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("process");
        String processId = instance.getProcessInstanceId();
        TaskQuery query = taskService.createTaskQuery().processInstanceId(processId);
        Task task = query.singleResult();
        return task.getId();
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
        TaskData taskData = new TaskData(convertMap(params));
        taskData.setActivitiDynamicId(task.getId());
        taskData.setId(task.getExecutionId());
        return taskData;
    }

    private String getString(Object object) {
        return object != null ? String.valueOf(object) : "";
    }

    private Map<String, String> convertMap(Map<String, Object> params){
        Map<String, String> newParams = new HashMap<>();
        for(Map.Entry<String, Object> entry: params.entrySet()) {
            newParams.put(entry.getKey(), getString(entry.getValue()));
        }
        return newParams;
    }


    public List<String> getStatuses() {
        return new ArrayList<>();
    }
}
