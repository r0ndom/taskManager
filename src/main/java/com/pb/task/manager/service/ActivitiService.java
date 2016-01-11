package com.pb.task.manager.service;

import com.pb.task.manager.model.FormData;
import com.pb.task.manager.model.State;
import com.pb.task.manager.model.Status;
import com.pb.task.manager.model.TaskData;
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
            if (params.containsKey("status")&&params.containsValue(filter.getStatus())) {
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
        taskData.setStatus(status);
        taskData.setAuthor("");
        taskData.setName(getString(params.get("name")));
        taskData.setExecutor("");
        String expectedTimeStr = getString(params.get("expectedTime"));
        Long expectedTime = (expectedTimeStr != null && !expectedTimeStr.equals("null"))
                ? Long.parseLong(expectedTimeStr) : null;
        taskData.setExpectedTime(expectedTime);
        taskData.setDescription(getString(params.get("description")));
        return taskData;
    }

    private String getString(Object object) {
        return String.valueOf(object);
    }
}
