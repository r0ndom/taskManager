package com.pb.task.manager.form.enums;

import com.pb.task.manager.dao.UserDao;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.FormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Mednikov on 22.02.2016.
 */
public class UserEnum implements Enumerable {

    private Map<String, String> params;

    public UserEnum(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public Map<String, String> getValues() {
        return params;
    }
}
