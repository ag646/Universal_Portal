package com.dal.universityPortal.service;

import com.dal.universityPortal.exceptions.UnsupportedUser;
import com.dal.universityPortal.exceptions.ValidationException;
import com.dal.universityPortal.model.Credential;
import com.dal.universityPortal.model.ResetCredential;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.model.UserType;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public interface AuthenticationService {

    void login(HttpSession session, Credential credential) throws SQLException, UnsupportedUser;

    void logout(HttpSession session);

    User getCurrentUser(HttpSession session);

    void sendPasswordCode(String username) throws SQLException, UnsupportedUser;

    void resetPassword(ResetCredential resetCredential) throws SQLException, ValidationException;

    String getRedirectLink(UserType type);
}
