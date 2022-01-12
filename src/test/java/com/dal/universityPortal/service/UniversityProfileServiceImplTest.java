package com.dal.universityPortal.service;

import com.dal.universityPortal.database.UniversityDao;
import com.dal.universityPortal.model.University;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

public class UniversityProfileServiceImplTest {
    @Mock
    UniversityDao universityDao;

    @InjectMocks
    UniversityProfileServiceImpl universityProfileServiceImpl;

    @Mock
    University university = new University();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProfileTest() throws SQLException {
        universityProfileServiceImpl.saveProfile(university);
        Mockito.verify(universityDao).insert(university);
    }

    @Test
    void readProfileTest() throws SQLException {
        universityProfileServiceImpl.readProfile(1);
        Mockito.verify(universityDao).fetchAll();

    }
}
