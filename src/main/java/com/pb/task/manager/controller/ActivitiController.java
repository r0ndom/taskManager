package com.pb.task.manager.controller;

import com.pb.task.manager.model.FormData;
import com.pb.task.manager.service.ActivitiService;
import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String submit(FormData data) {
        String id = service.submitForm(data);
        return "redirect:/task/" + id;
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
    public ModelAndView getTaskData(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("process/details");
        mav.addObject("taskData", service.getTaskData(id));
        return mav;
    }

    @ModelAttribute("formData")
    public FormData getFormData() {
        return new FormData();
    }
}
