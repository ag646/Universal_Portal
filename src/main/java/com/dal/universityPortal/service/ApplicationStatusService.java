package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Program;
import com.dal.universityPortal.model.University;

import java.sql.SQLException;

public interface ApplicationStatusService {

    Application getApplicationDetails(int id) throws SQLException;

    Program getProgramDetails(int id) throws SQLException;

    University getUniversityDetails(int id) throws SQLException;
}
