package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Application;

import java.sql.SQLException;

public interface ApplicationService {

    Boolean saveApplication(Application application) throws SQLException;

    Application readApplication(int id) throws SQLException;
}
