package com.dal.universityPortal.service;

import com.dal.universityPortal.model.User;
import com.dal.universityPortal.model.UserStatus;
import com.dal.universityPortal.model.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminPanelServiceImplTest {

    private static final String university1Name = "Dalhousie University";
    private static final String university1Email = "admin@dal.ca";
    private static final String university1password = "Dalhousie@123";
    private static final Integer university1Id = 101;
    private static final String university2Name = "Concordia University";
    private static final String university2Email = "admin@con.ca";
    private static final String university2password = "Concordia@123";
    private static final Integer university2Id = 102;

    private AdminPanelServiceImpl adminPanelserviceImpl;

    @BeforeEach
    public void setup(){
        adminPanelserviceImpl = Mockito.mock(AdminPanelServiceImpl.class);
    }

    @Test
    void getPendingStatusUniversitiesTest() throws SQLException {
        User user1 = new User(university1Name, university1Email, university1password, UserType.UNIVERSITY);
        User user2 = new User(university2Name, university2Email, university2password, UserType.UNIVERSITY);
        user1.setId(university1Id);
        user2.setId(university2Id);
        ArrayList<User> users = new ArrayList<>(Arrays.asList(user1, user2));
        Mockito.doReturn(users).when(adminPanelserviceImpl).getPendingStatusUniversities();
        assertEquals(2, users.size());
    }

    @Test
    void allowUniversityByIdTest() throws SQLException {
        User user = new User(university1Name, university1Email, university1password, UserType.UNIVERSITY);
        user.setId(university1Id);
        user.setStatus(UserStatus.ACTIVE);
        Mockito.doReturn(true).when(adminPanelserviceImpl).allowUniversityById(user);
        assertTrue(adminPanelserviceImpl.allowUniversityById(user));
    }

    @Test
    void denyUniversityByIdTest() throws SQLException {
        User user = new User(university1Name, university1Email, university1password, UserType.UNIVERSITY);
        user.setId(university1Id);
        user.setStatus(UserStatus.BLOCKED);
        Mockito.doReturn(true).when(adminPanelserviceImpl).denyUniversityById(user);
        assertTrue(adminPanelserviceImpl.denyUniversityById(user));
    }
}
