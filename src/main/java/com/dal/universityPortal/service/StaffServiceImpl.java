package com.dal.universityPortal.service;

import com.dal.universityPortal.database.StaffDao;
import com.dal.universityPortal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffDao staffDao;

    @Override
    public void addStaffUniversityMapping(User user, Integer universityId) throws SQLException {
        staffDao.mapUniversity(user, universityId);
    }
}
