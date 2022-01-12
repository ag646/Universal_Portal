package com.dal.universityPortal.service;

import com.dal.universityPortal.database.UserDao;
import com.dal.universityPortal.exceptions.UnsupportedUser;
import com.dal.universityPortal.exceptions.ValidationException;
import com.dal.universityPortal.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

import static com.dal.universityPortal.model.UserType.STUDENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class AuthenticationServiceImplTest {

    @Mock
    UserDao userDao;

    @Mock
    User user;

    @Mock
    ResetCredential resetCredential;

    @Mock
    HttpSession httpSession;


    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLogin() throws SQLException, UnsupportedUser {
        User user = new User("user", "user@email.com", "pass", STUDENT);
        user.setStatus(UserStatus.ACTIVE);
        Mockito.when(userDao.fetchOne(any())).thenReturn(user);
        Credential credential = new Credential();
        credential.setUsername("user");
        credential.setPassword("pass");
        authenticationService.login(httpSession, credential);
        Mockito.verify(httpSession).setAttribute("user", user);
    }

    @Test
    void shouldThrowExceptionWhenStatusIsPendingInLogin() throws SQLException, UnsupportedUser {
        User user = new User("user", "user@email.com", "pass", STUDENT);
        user.setStatus(UserStatus.PENDING);
        Mockito.when(userDao.fetchOne(any())).thenReturn(user);
        Credential credential = new Credential();
        credential.setUsername("user");
        credential.setPassword("pass");
        assertThrows(UnsupportedUser.class, () -> authenticationService.login(httpSession, credential));
        Mockito.verify(httpSession, Mockito.times(0)).setAttribute("user", user);
    }

    @Test
    void shouldThrowExceptionWhenPasswordDoesnotMatchInLogin() throws SQLException, UnsupportedUser {
        User user = new User("user", "user@email.com", "pass", STUDENT);
        user.setStatus(UserStatus.ACTIVE);
        Mockito.when(userDao.fetchOne(any())).thenReturn(user);
        Credential credential = new Credential();
        credential.setUsername("user");
        credential.setPassword("passsssssss");
        assertThrows(UnsupportedUser.class, () -> authenticationService.login(httpSession, credential));
        Mockito.verify(httpSession, Mockito.times(0)).setAttribute(anyString(), any());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesnotExistInLogin() throws SQLException, UnsupportedUser {
        Mockito.when(userDao.fetchOne(any())).thenReturn(null);
        Credential credential = new Credential();
        credential.setUsername("user");
        credential.setPassword("pass");
        assertThrows(UnsupportedUser.class, () -> authenticationService.login(httpSession, credential));
        Mockito.verify(httpSession, Mockito.times(0)).setAttribute(anyString(), any());
    }

    @Test
    void shouldLogout() {
        authenticationService.logout(httpSession);
        Mockito.verify(httpSession).removeAttribute("user");
    }

    @Test
    void getCurrentUser() {
        authenticationService.getCurrentUser(httpSession);
        Mockito.verify(httpSession).getAttribute("user");
    }


    @Test
    void sendPasswordCodeThrowsExceptionWhenWrongUsername() throws SQLException, UnsupportedUser {
        Mockito.when(userDao.fetchOne(any())).thenReturn(null);
        assertThrows(UnsupportedUser.class, () -> authenticationService.sendPasswordCode("testUser"));
    }

    @Test
    void resetPassword() throws SQLException, ValidationException {
        Mockito.when(userDao.fetchOne(any())).thenReturn(user);
        Mockito.when(user.isValid()).thenReturn(true);
        Mockito.when(user.getResetCode()).thenReturn(1);
        Mockito.when(resetCredential.getResetCode()).thenReturn(1);
        authenticationService.resetPassword(resetCredential);
        Mockito.verify(userDao).update(any(User.class));
    }

    @Test
    void resetPasswordWithValidationExceptionWhenNoUser() throws SQLException, ValidationException {
        Mockito.when(userDao.fetchOne(any())).thenReturn(null);
        assertThrows(ValidationException.class, () -> authenticationService.resetPassword(resetCredential));
        Mockito.verify(userDao, Mockito.times(0)).update(any(User.class));
    }

    @Test
    void resetPasswordWithValidationExceptionWhenInvalidUser() throws SQLException, ValidationException {
        Mockito.when(userDao.fetchOne(any())).thenReturn(null);
        Mockito.when(user.isValid()).thenReturn(false);
        Mockito.when(user.getResetCode()).thenReturn(1);
        Mockito.when(resetCredential.getResetCode()).thenReturn(1);
        assertThrows(ValidationException.class, () -> authenticationService.resetPassword(resetCredential));
        Mockito.verify(userDao, Mockito.times(0)).update(any(User.class));
    }

    @Test
    void getRedirectLinkForStaffReturnsUniversityDashboard() {
        //void getRedirectLink() {
        String handle = authenticationService.getRedirectLink(UserType.STAFF);
        assertEquals("/university/dashboard", handle);
    }

    @Test
    void getRedirectLinkForAdminReturnsAdminDashboard() {
        String handle = authenticationService.getRedirectLink(UserType.ADMIN);
        assertEquals("/admin/dashboard", handle);
    }
}
