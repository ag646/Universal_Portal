package com.dal.universityPortal.service;

import com.dal.universityPortal.model.User;

import java.sql.SQLException;

public interface StaffService {

    void addStaffUniversityMapping(User user, Integer universityId) throws SQLException;
}
