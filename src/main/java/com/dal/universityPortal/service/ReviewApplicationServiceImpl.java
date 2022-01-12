package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ReviewApplicationDao;
import com.dal.universityPortal.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewApplicationServiceImpl implements ReviewApplicationService {

    @Autowired
    private ReviewApplicationDao reviewApplicationDao;

    Logger logger = LogManager.getLogger(ReviewApplicationServiceImpl.class);


    @Override
    public List<Application> readList() throws SQLException {
        return reviewApplicationDao.fetchAll();
    }

    @Override
    public Application oneApplication(int id) throws SQLException {
        return reviewApplicationDao.fetchAllByParam(id);
    }

    @Override
    public Boolean saveReviewApplication(Application application) {
        try {
            reviewApplicationDao.update(application);
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }
}
