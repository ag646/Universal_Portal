package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Email;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.Properties;


public interface EmailService {

    boolean sendMail(Email email) throws MessagingException;

    boolean sendMailWithAttachment(Email email) throws FileNotFoundException, MessagingException;

    Email addDetails(String toAddress, String subject, String messageBody);

    Email addDetailsWithAttachment(String toAddress, String subject, String messageBody, String fileName);

    Properties getMailproperties();
}
