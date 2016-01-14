package com.pb.task.manager.model;

/**
 * Created by Mike on 12/31/2015.
 */
public class TaskData {

    private String id;
    private String activitiDynamicId;
    private String name;
    private State state;
    private User executor;
    private User author;
    private Long expectedTime;
    private String description;
    private Status status;

    public TaskData() {
    }

    public TaskData(String id, String activitiDynamicId, String name, State state, User executor, User author, Long expectedTime, String description, Status status) {
        this.id = id;
        this.activitiDynamicId = activitiDynamicId;
        this.name = name;
        this.state = state;
        this.executor = executor;
        this.author = author;
        this.expectedTime = expectedTime;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Long expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getActivitiDynamicId() {
        return activitiDynamicId;
    }

    public void setActivitiDynamicId(String activitiDynamicId) {
        this.activitiDynamicId = activitiDynamicId;
    }
}

