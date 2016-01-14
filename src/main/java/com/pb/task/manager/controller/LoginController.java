package com.pb.task.manager.controller;

import com.pb.task.manager.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private MessageUtils messageUtils;

    private static final String LOGIN = "login/login";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        String errorMessage = messageUtils.getMessage("messages.errorLogin");
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", errorMessage);
        }
        model.setViewName(LOGIN);
        return model;
    }

}