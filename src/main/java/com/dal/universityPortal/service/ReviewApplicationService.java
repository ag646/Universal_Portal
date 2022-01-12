package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Application;

import java.sql.SQLException;
import java.util.List;

public interface ReviewApplicationService {

    List<Application> readList() throws SQLException;

    Application oneApplication(int id) throws SQLException;

    Boolean saveReviewApplication(Application application) throws SQLException;
}
