package com.dal.universityPortal.service;

import com.dal.universityPortal.database.UserDao;
import com.dal.universityPortal.exceptions.ValidationException;
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
import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final String username = "username";
    private static final String email = "email";
    private static final String password = "password";

    @Mock
    UserDao userDao;

    @Mock
    User user = new User(username, email, password, UserType.STUDENT);

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setUp()  {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser() throws SQLException, ValidationException {
        Mockito.when(user.isValid()).thenReturn(true);
        Mockito.when(user.getTypeEnum()).thenReturn(UserType.STUDENT);
        userService.addUser(user);
        Mockito.verify(userDao).insert(user);
    }

    @Test
    void addUser_throwsDuplicateEntryError() throws SQLException {
        Mockito.doThrow(new SQLIntegrityConstraintViolationException()).when(userDao).insert(any());
        Mockito.when(user.isValid()).thenReturn(true);
        Mockito.when(user.getTypeEnum()).thenReturn(UserType.UNIVERSITY);
        assertThrows(ValidationException.class, () -> userService.addUser(user));
    }

    @Test
    void addUser_throwsValidationError() {
        Mockito.when(user.isValid()).thenReturn(false);
        assertThrows(ValidationException.class, () -> userService.addUser(user));
    }
}
