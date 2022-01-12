package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Program;
import com.dal.universityPortal.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationStatusServiceImplTest {

    private static final String university1Name = "Dalhousie University";
    private static final String universityDescription = "University in Halifax";
    private static final Integer id = 1;
    private static final String status = "Accepted";
    private static final Integer programId = 61;
    private static final String programName = "MACS";
    private static final Integer universityId = 10;
    private ApplicationStatusServiceImpl applicationStatusServiceImpl;

    @BeforeEach
    public void setup(){
        applicationStatusServiceImpl = Mockito.mock(ApplicationStatusServiceImpl.class);
    }

    @Test
    void getApplicationDetailsTest() throws SQLException {
        Application application = new Application();
        application.setApplication_id(id);
        application.setStatus(status);
        application.setProgram_id(programId);
        Application applicationDetails = application;
        Mockito.doReturn(applicationDetails).when(applicationStatusServiceImpl).getApplicationDetails(application.getApplication_id());
        assertEquals(applicationDetails, application);
    }

    @Test
    void getProgramDetailsTest() throws SQLException {
        Program program = new Program(id, programName, universityId);
        Program programDetails = program;
        Mockito.doReturn(programDetails).when(applicationStatusServiceImpl).getProgramDetails(program.getId());
        assertEquals(programDetails, program);
    }

    @Test
    void getUniversityDetailsTest() throws SQLException {
        University university = new University(id, university1Name, universityDescription);
        University universityDetails = university;
        Mockito.doReturn(universityDetails).when(applicationStatusServiceImpl).getUniversityDetails(university.getUserId());
        assertEquals(universityDetails, university);
    }
}
