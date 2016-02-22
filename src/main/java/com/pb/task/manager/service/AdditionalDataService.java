package com.pb.task.manager.service;

import com.pb.task.manager.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by Mednikov on 22.02.2016.
 */
@Service("additionalInfo")
public class AdditionalDataService {

    private Date date;

    @Autowired
    private UserDao userDao;

    public String getStartDate() {
        if (date == null) {
            this.date = new Date();
        }
        return date.toString();
    }

    public String getEndDate() {
        return new Date().toString();
    }

    public String getUser() {
        return userDao.getCurrentUser().getLdap();
    }

    public Map<String, String> getUsers() {
        return userDao.findAllMap();
    }

}
