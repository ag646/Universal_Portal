package com.dal.universityPortal.controller;

import com.dal.universityPortal.exceptions.ValidationException;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

import static com.dal.universityPortal.constant.ErrorConstant.UNEXPECTED_ERROR;
import static com.dal.universityPortal.constant.UrlConstant.LOGIN;
import static com.dal.universityPortal.constant.UrlConstant.REGISTRATION;


@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(REGISTRATION)
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(REGISTRATION)
    public String saveRegistration(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        try {
            userService.addUser(user);
            return String.format("redirect:%s", LOGIN);
        } catch (ValidationException exception) {
            model.addAttribute("errors", exception.getErrors());
        } catch (SQLException exception) {
            model.addAttribute("errors", UNEXPECTED_ERROR);
        }
        return "registration";
    }
}
