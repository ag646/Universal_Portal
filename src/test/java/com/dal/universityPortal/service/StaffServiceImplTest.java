package com.dal.universityPortal.service;

import com.dal.universityPortal.database.StaffDao;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.model.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class StaffServiceImplTest {

    @Mock
    StaffDao staffDao;

    @InjectMocks
    StaffServiceImpl staffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStaffUniversityMapping() throws SQLException {
        User user = new User("staff", "staff@dal.ca","paasss", UserType.STAFF);
        staffService.addStaffUniversityMapping(user, 1);
        Mockito.verify(staffDao).mapUniversity(user, 1);
    }
}
