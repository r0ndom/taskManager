package com.pb.task.manager.model;

/**
 * Created by Mike on 12/31/2015.
 */
public class TaskData {

    private String id;
    private String name;
    private State state;
    private String executor;
    private String author;
    private Long expectedTime;
    private String description;
    private Status status;

    public TaskData() {
    }

    public TaskData(String id, String name, State state, String executor, String author, Long expectedTime, String description, Status status) {
        this.id = id;
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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
}

