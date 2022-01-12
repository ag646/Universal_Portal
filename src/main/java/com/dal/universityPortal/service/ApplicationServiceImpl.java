package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ApplicationDao;
import com.dal.universityPortal.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDao applicationDao;
    Logger logger = LogManager.getLogger(ApplicationServiceImpl.class);

    @Override
    public Boolean saveApplication(Application application) {
        try {
            applicationDao.insert(application);
        } catch (SQLException e){
            logger.error(e);
        }
        return null;
    }

    @Override
    public Application readApplication(int id) throws SQLException {
        return applicationDao.fetchAllByParam(id);
    }
}
