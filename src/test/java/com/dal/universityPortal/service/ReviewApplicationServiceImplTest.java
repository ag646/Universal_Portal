package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ReviewApplicationDao;
import com.dal.universityPortal.model.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

public class ReviewApplicationServiceImplTest {
    private static final int application_id = 1;

    @Mock
    ReviewApplicationDao reviewApplicationDao;

    @InjectMocks
    ReviewApplicationServiceImpl reviewApplicationService;

    @Mock
    Application application = new Application();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void readList() throws SQLException {
        reviewApplicationService.readList();
        Mockito.verify(reviewApplicationDao).fetchAll();
    }

    @Test
    void oneApplication() throws SQLException {
        reviewApplicationService.oneApplication(application_id);
        Mockito.verify(reviewApplicationDao).fetchAllByParam(application_id);
    }

    @Test
    void saveReviewApplication() throws SQLException {
        application.setStatus("Accept");
        reviewApplicationService.saveReviewApplication(application);
        Mockito.verify(reviewApplicationDao).update(application);
    }
}
