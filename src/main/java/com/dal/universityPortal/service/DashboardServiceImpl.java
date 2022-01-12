package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ApplicationDao;
import com.dal.universityPortal.database.PaymentDao;
import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Dashboard;
import com.dal.universityPortal.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    ApplicationDao applicationDao;

    @Autowired
    PaymentDao paymentDao;

    @Override
    public List<Application> readListApplication(int studentId) throws SQLException {
        return applicationDao.fetchApplication(studentId);
    }

    @Override
    public List<Payment> readListPayment(int studentId) throws SQLException {
        return paymentDao.fetchPayment(studentId);
    }

    @Override
    public Dashboard populateAttributes(List<Application> applicationList) {
        Dashboard dashboard = new Dashboard();
        for (Application application : applicationList) {
            if (application.getStatus().equals("New") || application.getStatus().equals("In-process")) {
                dashboard.incrementInProcessApplications();
            } else if (application.getStatus().equals("Accept")) {
                dashboard.incrementSuccessfulApplications();
            } else if (application.getStatus().equals("Reject")) {
                dashboard.incrementRejectedApplications();
            }
        }
        return dashboard;
    }
}
