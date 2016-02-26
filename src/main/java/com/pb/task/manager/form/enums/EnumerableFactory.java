package com.pb.task.manager.form.enums;

import com.pb.task.manager.dao.UserDao;
import org.activiti.engine.form.FormProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mednikov on 22.02.2016.
 */
@Component
public class EnumerableFactory {

    public final static String DEFAULT_ENUM = "enum";
    public final static String USER_ENUM = "users";

    @Autowired
    private UserDao userDao;

    public Enumerable buildEnum(FormProperty formProperty) {
        String type = formProperty.getType().getName();
        Enumerable enumerable = null;
        switch (type) {
            case DEFAULT_ENUM:
                enumerable = new DefaultEnum(formProperty);
                break;

            case USER_ENUM:
                enumerable = new UserEnum(userDao.findAllMap());
                break;

            default:
                enumerable = new UsualTypeNotEnum();
                break;
        }
        return enumerable;
    }
}