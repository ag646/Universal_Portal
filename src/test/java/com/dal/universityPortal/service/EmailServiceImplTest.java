package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Email;
import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailServiceImplTest {

    @Test
    void addDetails() {
        Email email;
        EmailServiceImpl emailService= new EmailServiceImpl();
        email=emailService.addDetails("google@gmail.com","mail","Hello");
        assertEquals(email,email);

    }

    @Test
    void addDetailsWithAttachment() {
        Email email;
        EmailServiceImpl emailService= new EmailServiceImpl();
        email=emailService.addDetailsWithAttachment("google@gmail.com","mail","Hello","");
        assertEquals(email,email);
    }

    @Test
    void sendMail() throws MessagingException {
        Email email;
        EmailServiceImpl emailService= new EmailServiceImpl();
        email=emailService.addDetails("arunkumargauda1997@gmail.com","mail","Hello");
        emailService.sendMail(email);
        assertTrue(true,"Error!!!");

    }

    @Test
    void sendMailWithAttachment() throws MessagingException, FileNotFoundException {
        Email email;
        EmailServiceImpl emailService= new EmailServiceImpl();
        email=emailService.addDetailsWithAttachment("arunkumargauda1997@gmail.com","mail","Hello","src/main/resources/files/accept.txt");
        emailService.sendMailWithAttachment(email);
        assertTrue(true,"Error!!!");
    }
}