package com.pb.task.manager.controller;

import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.FormData;
import com.pb.task.manager.model.Role;
import com.pb.task.manager.model.Status;
import com.pb.task.manager.model.TaskData;
import com.pb.task.manager.model.filter.TaskSearchFilter;
import com.pb.task.manager.service.ActivitiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 12/31/2015.
 */
@Controller
@RequestMapping("/app/tasks")
public class TaskController {

    @Autowired
    private ActivitiService service;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showSearchPage() {
        return getMav(service.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView search(TaskSearchFilter filter) {
        return getMav(service.search(filter));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView showCreatePage() {
        ModelAndView mav = new ModelAndView("process/create");
        mav.addObject("author", userDao.getCurrentUser().getLdap());
        return mav;
    }

    @RequestMapping(value = "/submitTaskForm", method = RequestMethod.POST)
    public String submit(FormData data) {
        String id = service.submitForm(data);
        if (data.getMap().containsKey("status")) {
            if (data.getMap().get("status").equals("done"))
                return "redirect:/";
        }
        return "redirect:/app/tasks/show/" + id;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getTaskData(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("process/input");
        mav.addObject("taskData", service.getTaskData(id));
        mav.addObject("executor", userDao.getCurrentUser().getLdap());
        return mav;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView showTaskData(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView("process/details");
        mav.addObject("taskData", service.getTaskData(id));
        mav.addObject("isWritable", service.checkUserAccess(id));
        return mav;
    }

    @ModelAttribute("formData")
    public FormData getFormData() {
        return new FormData();
    }

    @ModelAttribute("taskSearchFilter")
    public TaskSearchFilter getFilter() {
        return new TaskSearchFilter();
    }

    private ModelAndView getMav(List<TaskData> list) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tasks", list);
        mav.addObject("statusList", service.getStatuses());
        mav.addObject("executorList", userDao.findByRole(Role.ROLE_DEVELOPER));
        mav.addObject("authorList", userDao.findByRole(Role.ROLE_MANAGER));
        return mav;
    }
}
