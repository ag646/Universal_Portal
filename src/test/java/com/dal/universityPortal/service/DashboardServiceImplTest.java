package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardServiceImplTest {

    private DashboardServiceImpl dashboardServiceImpl;

    @Autowired
    private DashboardService dashboardService;

    @BeforeEach
    public void setup() {
        dashboardServiceImpl = Mockito.mock(DashboardServiceImpl.class);
    }

    @Test
    void readListApplicationTest() throws SQLException {
        int student_id = 1;
        List<Application> applications;
        List<Application> applications1;

        applications = dashboardServiceImpl.readListApplication(student_id);
        applications1 = dashboardServiceImpl.readListApplication(1);

        assertEquals(applications, applications1);
    }

    @Test
    void readListPaymentTest() throws SQLException {
        int student_id = 1;
        List<Payment> payment1;
        List<Payment> payment2;

        payment1 = dashboardServiceImpl.readListPayment(student_id);
        payment2 = dashboardServiceImpl.readListPayment(1);

        assertEquals(payment1, payment2);
    }
}