package com.dal.universityPortal.controller;

import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Dashboard;
import com.dal.universityPortal.model.Payment;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.AuthenticationService;
import com.dal.universityPortal.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static com.dal.universityPortal.constant.UrlConstant.DASHBOARD;
import static com.dal.universityPortal.constant.UrlConstant.STUDENT;


@Controller
@RequestMapping(STUDENT)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(DASHBOARD)
    public String loadDashboard(Model model, HttpServletRequest request) throws SQLException {
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        int studentId = currentUser.getId();
        List<Application> applicationList = dashboardService.readListApplication(studentId);
        model.addAttribute("listApplication", applicationList);
        Dashboard dashboard = dashboardService.populateAttributes(applicationList);
        List<Payment> paymentList = dashboardService.readListPayment(studentId);
        for (Payment payment : paymentList) {
            dashboard.appPayment(payment.getAmount());
        }
        model.addAttribute("dashboard", dashboard);
        model.addAttribute("listPayment", paymentList);
        return "dashboard";
    }
}
