package com.dal.universityPortal.service;

import com.dal.universityPortal.constant.EmailConstant;
import com.dal.universityPortal.model.Email;
import com.dal.universityPortal.validator.EmailAddressValidator;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService{

    @Override
    public Properties getMailproperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.ssl.trust", EmailConstant.HOST);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", EmailConstant.HOST);
        properties.put("mail.smtp.user", EmailConstant.USER);
        properties.put("mail.smtp.password", EmailConstant.PASSWORD);
        properties.put("mail.smtp.port", EmailConstant.PORT1);
        properties.put("mail.smtp.auth", "true");
        return properties;
    }

    @Override
    public Email addDetails(String toAddress, String subject, String messageBody) {
        Email email= new Email();
        if(EmailAddressValidator.isValid(toAddress)){
            email.setToAddress(toAddress);
            email.setSubject(subject);
            email.setMessageBody(messageBody);
            return email;
        }
        else {
            return email;
        }
    }

    @Override
    public Email addDetailsWithAttachment(String toAddress, String subject, String messageBody, String fileName) {
        Email email = new Email();
        if(EmailAddressValidator.isValid(toAddress)){
            email.setToAddress(toAddress);
            email.setSubject(subject);
            email.setMessageBody(messageBody);
            email.setFileName(fileName);
            return email;
        }
        else {
            return email;
        }
    }

    @Override
    public boolean sendMail(Email email) throws MessagingException {
        Properties properties = getMailproperties();
        Authenticator authenticator  = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConstant.USER, EmailConstant.PASSWORD);
            }
        };
        Session session = Session.getDefaultInstance(properties,authenticator);
        MimeMessage message=new MimeMessage(session);
        message.setFrom(new InternetAddress(EmailConstant.USER));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getToAddress()));
        message.setSubject(Email.getSubject());
        message.setText(Email.getMessageBody());
        Transport transport = session.getTransport("smtp");
        transport.connect(EmailConstant.HOST,587,EmailConstant.USER,"");//If Sender has 2 factor verification then App password is needed else keep an empty string
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return true;
    }

    @Override
    public boolean sendMailWithAttachment(Email email) throws MessagingException {
        Properties props = getMailproperties();
        Authenticator authenticator  = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConstant.USER, EmailConstant.PASSWORD);
            }
        };
        Session session = Session.getDefaultInstance(props,authenticator);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(EmailConstant.USER));
        mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getToAddress()));
        mimeMessage.setSubject(Email.getSubject());
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(Email.getMessageBody());
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        DataSource dataSource =new FileDataSource(email.getFileName());
        messageBodyPart2.setDataHandler(new DataHandler(dataSource));
        messageBodyPart2.setFileName(email.getFileName());
        Multipart multipart =new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart2);
        mimeMessage.setContent(multipart);
        Transport transport = session.getTransport(EmailConstant.PROTOCOL);
        transport.connect(EmailConstant.HOST,EmailConstant.PORT,EmailConstant.USER,"");//If Sender has 2 factor verification then App password is needed else keep an empty string
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        return true;
    }
}
