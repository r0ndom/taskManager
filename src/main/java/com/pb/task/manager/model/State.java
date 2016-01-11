package com.pb.task.manager.model;

/**
 * Created by Mednikov on 06.01.2016.
 */
public enum State {
    CREATED("createTask"),
    EXPECTED_TIME_DEFINED("expectedTime"),
    IN_PROGRESS("inProgress"),
    ON_TESTING("onTesting"),
    STATUS_DEFINED("setTaskStatus");

    private final String name;

    State(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }

    public static State getState(String s) {
        for (State state : State.values()) {
            if (state.getName().equals(s))
                return state;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
