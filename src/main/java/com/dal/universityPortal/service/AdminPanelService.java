package com.dal.universityPortal.service;

import com.dal.universityPortal.model.User;
import java.sql.SQLException;
import java.util.List;

public interface AdminPanelService {

    List<User> getPendingStatusUniversities() throws SQLException;

    boolean allowUniversityById(User user) throws SQLException;

    boolean denyUniversityById(User user) throws SQLException;
}
