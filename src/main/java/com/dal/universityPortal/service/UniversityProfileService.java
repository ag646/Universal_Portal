package com.dal.universityPortal.service;

import com.dal.universityPortal.model.University;

import java.sql.SQLException;

public interface UniversityProfileService {

    Boolean saveProfile(University university) throws SQLException;

    University readProfile(int id) throws SQLException;

    Boolean updateProfile(University university) throws SQLException;
}
