package com.pb.task.manager.controller;

import com.pb.task.manager.model.FormData;
import com.pb.task.manager.service.ActivitiService;
import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mednikov on 05.01.2016.
 */
@Controller
public class ActivitiController {

    @Autowired
    private ActivitiService service;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreatePage() {
        return "process/create";
    }

    @RequestMapping(value = "/submitTaskForm", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void submit(FormData data) {
        service.submitForm(data, true);
    }

    @ModelAttribute("formData")
    public FormData getFormData() {
        return new FormData();
    }
}
