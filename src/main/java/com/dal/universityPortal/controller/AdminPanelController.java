package com.dal.universityPortal.controller;

import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.AdminPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
@RequestMapping(ADMIN)
public class AdminPanelController {

    @Autowired
    private AdminPanelService adminPanelService;

    @GetMapping(DASHBOARD)
    public String getUniversities(Model model) throws SQLException {
        model.addAttribute("listUniversities", adminPanelService.getPendingStatusUniversities());
        return "admin_panel";
    }

    @GetMapping(ALLOW_UNIVERSITY + "/{id}")
    public String allowUniversity(@PathVariable(value = "id") int id, Model model) throws SQLException {
        User user = new User();
        user.setId(id);
        this.adminPanelService.allowUniversityById(user);
        model.addAttribute("listUniversities", adminPanelService.getPendingStatusUniversities());
        return String.format("redirect:%s%s", ADMIN, DASHBOARD);
    }

    @GetMapping(DENY_UNIVERSITY + "/{id}")
    public String denyUniversity(@PathVariable (value = "id") int id, Model model) throws SQLException {
        User user = new User();
        user.setId(id);
        this.adminPanelService.denyUniversityById(user);
        model.addAttribute("listUniversities", adminPanelService.getPendingStatusUniversities());
        return String.format("redirect:%s%s", ADMIN, DASHBOARD);
    }
}
