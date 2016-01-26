package com.pb.task.manager.controller;

import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.FormData;
import com.pb.task.manager.model.Role;
import com.pb.task.manager.model.TaskData;
import com.pb.task.manager.model.filter.TaskSearchFilter;
import com.pb.task.manager.service.ActivitiService;

import com.pb.task.manager.util.FormUtils;
import org.activiti.engine.form.FormProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        String taskId = service.startProcess();
        mav.addObject("taskData", FormUtils.convertTo(service.getFormProperty(taskId)));
        mav.addObject("taskId", taskId);
        mav.addObject("isWritable", true);
        return mav;
    }

    @RequestMapping(value = "/submitTaskForm", method = RequestMethod.POST)
    public String submit(FormData data) {
        System.out.println("Form data id: " + data.getId());
        Map<String, Object> taskParams = service.getVariables(data.getId());
        if (service.getFormKey(data.getId()).equals("expectedTime")&&!data.getMap().containsKey("edit")) {
            data.getMap().put("edit", "false");
        }
        if (!taskParams.containsKey("author") && data.getMap().get("author") == null) {
            data.getMap().put("author", userDao.getCurrentUser().getLdap());
        } else if(!taskParams.containsKey("executor") && data.getMap().get("executor") == null){
            data.getMap().put("executor", userDao.getCurrentUser().getLdap());
        }
//        if (!taskParams.containsKey("startDate") || data.getMap().get("startDate") == null) {
//            data.getMap().put("startDate", String.valueOf(new Date()));
//        }
//        if (!taskParams.containsKey("endDate") && data.getMap().get("endDate") == null) {
//            data.getMap().put("endDate", String.valueOf(new Date()));
//        }
        String executionId = service.submitForm(data);
        String taskId = service.getTaskIdByExecutionId(executionId);
        if (data.getMap().containsKey("status")) {
            if (data.getMap().get("status").equals("завершена")) {
                service.deleteTask(taskId);
                return "redirect:/app/tasks/";
            }
        }
        return "redirect:/app/tasks/show/" + taskId;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getTaskData(@PathVariable("id") String taskId) {
        ModelAndView mav = new ModelAndView("process/details");
        mav.addObject("taskData", FormUtils.convertTo(service.getFormProperty(taskId)));
        mav.addObject("executor", userDao.getCurrentUser().getLdap());
        mav.addObject("isWritable", true);
        mav.addObject("taskId", taskId);
        mav.addObject("isSubmit", true);
        return mav;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public ModelAndView showTaskData(@PathVariable("id") String taskId) {
        ModelAndView mav = new ModelAndView("process/details");
        mav.addObject("taskData", service.getFormProperty(taskId));
        mav.addObject("isWritable", false);
        mav.addObject("taskId", taskId);
        mav.addObject("isEditor", service.checkUserAccess(taskId));
        mav.addObject("isDeleted", service.checkDeleteAccess(taskId));
        if (service.getFormKey(taskId).equals("expectedTime")) {
            mav.addObject("editDescr", service.checkDeleteAccess(taskId));
        }
        return mav;
    }

    @RequestMapping(value = "/archive/", method = RequestMethod.GET)
    public ModelAndView showArchive() {
        ModelAndView mav = new ModelAndView("archive/archive");
        mav.addObject("tasks", service.getArchivedTasks());
        return mav;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteTaskData(@PathVariable("id") String taskId) {
        service.deleteTask(taskId);
        return "redirect:/app/tasks/";
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
