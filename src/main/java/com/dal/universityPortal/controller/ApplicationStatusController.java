package com.dal.universityPortal.controller;

import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Program;
import com.dal.universityPortal.model.University;
import com.dal.universityPortal.service.ApplicationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

import static com.dal.universityPortal.constant.UrlConstant.APPLICATION_STATUS;
import static com.dal.universityPortal.constant.UrlConstant.STUDENT;

@Controller
@RequestMapping(STUDENT)
public class ApplicationStatusController {

    @Autowired
    private ApplicationStatusService applicationStatusService;

    @GetMapping(APPLICATION_STATUS +"/{id}")
    public String showApplicationStatus(@PathVariable(value = "id") int id, Model model) throws SQLException {
        Application application = new Application();
        application.setApplication_id(id);
        application = applicationStatusService.getApplicationDetails(application.getApplication_id());
        Program program = applicationStatusService.getProgramDetails(application.getProgram_id());
        University university = applicationStatusService.getUniversityDetails(program.getUniversityId());
        model.addAttribute("applicationStatus", application);
        model.addAttribute("program", program);
        model.addAttribute("university", university);
        return "application_status";
    }
}
