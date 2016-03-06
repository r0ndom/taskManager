package com.pb.task.manager.controller;

import com.pb.task.manager.dao.UserDao;
import com.pb.task.manager.model.Role;
import com.pb.task.manager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mednikov on 13.01.2016.
 */
@Controller
@RequestMapping("/app/users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("users/users");
        mav.addObject("users", userDao.findAll());
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createPage() {
        ModelAndView mav = new ModelAndView("users/create");
        mav.addObject("roleList", Role.getPermittedValues());
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(User user) {
        userDao.create(user);
        return "redirect:/app/users/";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfile() {
        ModelAndView mav = new ModelAndView("users/profile");
        mav.addObject("user", userDao.getCurrentUser());
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(User user) {
        User current = userDao.getCurrentUser();
        user.setId(current.getId());
        user.setLdap(current.getLdap());
        user.setRole(current.getRole());
        userDao.update(user);
        return "redirect:/app/users/profile/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        ModelAndView mav = new ModelAndView("users/edit");
        mav.addObject("currentUser", userDao.getCurrentUser());
        return mav;
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }

}
