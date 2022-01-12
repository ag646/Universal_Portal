package com.dal.universityPortal.service;

import com.dal.universityPortal.exceptions.ValidationException;
import com.dal.universityPortal.model.User;

import java.sql.SQLException;

public interface UserService {

    void addUser(User user) throws SQLException, ValidationException;
}
