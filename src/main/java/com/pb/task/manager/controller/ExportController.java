package com.pb.task.manager.controller;

import com.google.gdata.util.ServiceException;
import com.pb.task.manager.service.ActivitiService;
import com.pb.task.manager.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * Created by Mednikov on 09.03.2016.
 */
@Controller
@RequestMapping("/app")
public class ExportController {

    @Autowired
    private ExportService service;
    @Autowired
    private ActivitiService activitiService;

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public String export() throws IOException, ServiceException {
        String url = service.generateReport(activitiService.findAll());
        return "redirect:" + url;
    }
}
