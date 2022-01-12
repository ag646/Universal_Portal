package com.dal.universityPortal.service;

import com.dal.universityPortal.database.UserDao;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.model.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;

@Service
public class AdminPanelServiceImpl implements AdminPanelService{

    Logger logger = LogManager.getLogger(AdminPanelServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getPendingStatusUniversities() throws SQLException {
        List<User> listUniversities = userDao.fetchAllPendingUsers();
        return listUniversities;
    }

    @Override
    public boolean allowUniversityById(User user) {
        try {
            user.setStatus(UserStatus.ACTIVE);
            userDao.setUserStatus(user);
        } catch (Exception exception) {
            logger.error(String.format("Error changing status of university: %s", exception));
        }
        return true;
    }

    @Override
    public boolean denyUniversityById(User user) {
        try {
            user.setStatus(UserStatus.BLOCKED);
            userDao.setUserStatus(user);
        } catch (Exception exception) {
            logger.error(String.format("Error changing status of university: %s", exception));
        }
        return true;
    }
}
