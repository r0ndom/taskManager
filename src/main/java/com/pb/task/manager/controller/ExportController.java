package com.pb.task.manager.controller;

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

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public String export() throws IOException {
        service.createFile();
        return "redirect:/app/tasks/";
    }
}
