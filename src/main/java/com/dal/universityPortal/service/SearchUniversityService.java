package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Program;

import com.dal.universityPortal.model.University;

import java.sql.SQLException;
import java.util.List;

public interface SearchUniversityService {

    University getUniversityDetails(University university) throws SQLException;
    List<Program> getProgramDetails(int id) throws SQLException;
}
