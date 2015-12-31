package com.pb.task.manager.controller;

import com.pb.task.manager.dao.TaskDao;
import com.pb.task.manager.model.Task;
import com.pb.task.manager.model.filter.TaskSearchFilter;
import com.pb.task.manager.util.StubData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 12/31/2015.
 */
@Controller
public class SearchTaskController {

    @Autowired
    private TaskDao taskDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showSearchPage() {
        return getMav(taskDao.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView search(TaskSearchFilter filter) {
        return getMav(taskDao.search(filter));
    }

    @ModelAttribute("taskSearchFilter")
    public TaskSearchFilter getFilter() {
        return new TaskSearchFilter();
    }

    private ModelAndView getMav(List<Task> list) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tasks", list);
        mav.addObject("statusList", StubData.getStatuses());
        mav.addObject("executorList", StubData.getExecutors());
        mav.addObject("authorList", StubData.getAuthors());
        return mav;
    }

}
