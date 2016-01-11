package com.pb.task.manager.model;

public enum Status {

    NEW("new"),
    DEVELOPING("development"),
    TESTING("testing"),
    DONE("done"),
    REOPENED("reopened");

    private final String name;

    Status(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public static Status getStatus(String s) {
        for (Status status : Status.values()) {
            if (status.getName().equals(s))
                return status;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}