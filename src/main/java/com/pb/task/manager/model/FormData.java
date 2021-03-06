package com.pb.task.manager.model;

import java.util.Map;

/**
 * Created by Mednikov on 05.01.2016.
 */
public class FormData {
    private String id;
    private Map<String, String> map;

    public FormData() {
    }

    public FormData(String id, Map<String, String> map) {
        this.id = id;
        this.map = map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
