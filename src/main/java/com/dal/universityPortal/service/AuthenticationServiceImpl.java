package com.dal.universityPortal.service;

import com.dal.universityPortal.database.UserDao;
import com.dal.universityPortal.exceptions.UnsupportedUser;
import com.dal.universityPortal.exceptions.ValidationException;
import com.dal.universityPortal.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Random;

import static com.dal.universityPortal.constant.CommonConstant.*;
import static com.dal.universityPortal.constant.ErrorConstant.CREDENTIALS_MISMATCH_ERROR;
import static java.util.Objects.isNull;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    Logger logger = LogManager.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public void login(HttpSession session, Credential credential) throws SQLException, UnsupportedUser {
        User user = userDao.fetchOne(credential.getUsername());
        if (isNull(user) || !isSupportedUser(credential.getPassword(), user)) {
            throw new UnsupportedUser();
        }
        session.setAttribute("user", user);
    }

    private boolean isSupportedUser(String password, User user) {
        return password.equals(user.getPassword()) && user.getStatus().equals(UserStatus.ACTIVE);
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }

    @Override
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @Override
    public void sendPasswordCode(String username) throws SQLException, UnsupportedUser {
        User user = userDao.fetchOne(username);
        if (isNull(user)) {
            throw new UnsupportedUser();
        }
        Integer randomCode = new Random().nextInt(RESET_CODE_RANGE);
        Email email = emailService.addDetails(user.getEmail(), RESET_PASSWORD_MAIL_SUBJECT, String.format(RESET_PASSWORD_MAIL_FORMAT, randomCode));
        try{
            emailService.sendMail(email);
        } catch (MessagingException exception) {
            logger.error(String.format("Email sending error: %s", exception));
        }
        userDao.setResetCode(user, randomCode);
    }

    @Override
    public void resetPassword(ResetCredential resetCredential) throws SQLException, ValidationException {
        User user = userDao.fetchOne(resetCredential.getUsername());
        if(isNull(user) || !user.getResetCode().equals(resetCredential.getResetCode())) {
            throw new ValidationException(CREDENTIALS_MISMATCH_ERROR);
        }
        user.setPassword(resetCredential.getPassword());
        if (!user.isValid()) {
            throw new ValidationException(user.getErrorMessages());
        }
        userDao.update(user);
    }

    @Override
    public String getRedirectLink(UserType type) {
        String handle = type.equals(UserType.STAFF) ? UserType.UNIVERSITY.toString() : type.toString();
        return String.format("/%s/dashboard", handle.toLowerCase());
    }
}
