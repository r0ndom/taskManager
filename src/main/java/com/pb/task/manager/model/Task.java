package com.pb.task.manager.model;

/**
 * Created by Mike on 12/31/2015.
 */
public class Task {

    private Long id;
    private String name;
    private Status status;
    private String executor;
    private String author;

    public Task() {
    }

    public Task(Long id, String name, Status status, String executor, String author) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.executor = executor;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
}
