package com.dal.universityPortal.controller;

import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.ApplicationService;
import com.dal.universityPortal.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
@RequestMapping(STUDENT)
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(LOAD_APPLICATION + "/{id}")
    public String loadApplication(@PathVariable (value = "id") int id, Model model) throws SQLException {
        Application application = new Application();
        application.setProgram_id(id);
        model.addAttribute("app", application);
        return "application_form";
    }

    @PostMapping(SAVE_APPLICATION + "/{id}")
    public String saveApplication(@PathVariable (value = "id") int id, @ModelAttribute("application") Application application, HttpServletRequest request) throws SQLException {
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        application.setProgram_id(id);
        application.setStudent_id(currentUser.getId());
        applicationService.saveApplication(application);
        application=applicationService.readApplication(currentUser.getId());
        return String.format("redirect:%s%s/%s", STUDENT, LOAD_PAYMENT, application.getApplication_id());
    }
}
