package com.pb.task.manager.model;

import com.pb.task.manager.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Mike on 12/31/2015.
 */
public class TaskData {

//    @Autowired
//    private UserDao userDao;

    private String id;
    private String activitiDynamicId;

    Map<String, String> params;

    public TaskData(Map<String, String> params) {
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return params.get("name");
    }

//    public User getExecutor() {
//        return userDao.findByLdap(params.get("executor"));
//    }
//
//    public User getAuthor() {
//        String authorLdap = params.get("author");
//        return userDao.findByLdap(authorLdap);
//    }

    public State getState() {
        return State.getState(params.get("state"));
    }

    public Long getExpectedTime() {
        String expectedTimeStr = params.get("expectedTime");
        return (expectedTimeStr != null && !expectedTimeStr.equals(""))
                ? Long.parseLong(expectedTimeStr) : null;
    }

    public String getDescription() {
        return params.get("description");
    }

    public Status getStatus() {
        return Status.getStatus(params.get("status"));
    }

    public String getActivitiDynamicId() {
        return activitiDynamicId;
    }

    public void setActivitiDynamicId(String activitiDynamicId) {
        this.activitiDynamicId = activitiDynamicId;
    }
}

