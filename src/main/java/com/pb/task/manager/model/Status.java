package com.pb.task.manager.model;

public enum Status {

    ALL("all", "Все"),
    NEW("new", "Новая"),
    DEVELOPING("development", "Разработка"),
    TESTING("testing", "Тестирование"),
    DONE("done", "Выполнено"),
    REOPENED("reopened", "Доработка");

    private final String name;

    private final String viewName;

    Status(String strName, String viewName) {
        this.name = strName;
        this.viewName = viewName;
    }

    public String getName() {
        return name;
    }

    public String getViewName() {
        return viewName;
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