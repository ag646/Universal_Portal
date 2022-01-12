package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ApplicationDao;
import com.dal.universityPortal.model.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

class ApplicationServiceImplTest {

    @Mock
    ApplicationDao applicationDao;

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    Application application = new Application();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveApplication() throws SQLException {
        applicationService.saveApplication(application);
        Mockito.verify(applicationDao).insert(application);
    }

    @Test
    void readApplication() throws SQLException {
        applicationService.readApplication(1);
        Mockito.verify(applicationDao).fetchAllByParam(1);
    }
}