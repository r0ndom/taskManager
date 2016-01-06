package com.pb.task.manager.model;

/**
 * Created by Mednikov on 06.01.2016.
 */
public enum State {
    CREATED,
    EXPECTED_TIME_DEFINED,
    IN_PROGRESS,
    ON_TESTING,
    STATUS_DEFINED;

    public static State getState(String s) {
        if (s.equals("createTask")) {
            return State.CREATED;
        } else if (s.equals("expectedTime")) {
            return State.EXPECTED_TIME_DEFINED;
        } else if (s.equals("inProgress")) {
            return State.IN_PROGRESS;
        } else if (s.equals("onTesting")) {
            return State.ON_TESTING;
        } else if (s.equals("setTaskStatus")) {
            return State.STATUS_DEFINED;
        }
        return null;
    }
}
