package com.dal.universityPortal.model;

public class Email {
    static String toAddress;
    static String subject;
    static String messageBody;
    static String fileName;

    public Email() {
    }

    public Email(String toAddress,String subject, String messageBody, String fileName){
        this.toAddress=toAddress;
        this.subject=subject;
        this.messageBody=messageBody;
        this.fileName=fileName;
    }

    public Email(String toAddress,String subject, String messageBody){
        this.toAddress = toAddress;
        this.subject = subject;
        this.messageBody=messageBody;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress=toAddress;
    }

    public static String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public static String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
