package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Dashboard;
import com.dal.universityPortal.model.Payment;

import java.sql.SQLException;
import java.util.List;

public interface DashboardService {

    List<Application> readListApplication(int studentId) throws SQLException;

    List<Payment> readListPayment(int studentId) throws SQLException;

    Dashboard populateAttributes(List<Application> applicationList);
}
