package com.dal.universityPortal.model;

public class Payment {
    private Integer applicationId;
    private Integer amount;
    private String name;
    private String cardNumber;
    private String expiryDate;
    private String CVV;
    private Integer studentId;

    public Payment() {
    }

    public Payment(Integer applicationId, Integer amount, String name, String cardNumber, String expiryDate, String CVV, Integer studentId) {
        this.applicationId = applicationId;
        this.amount = amount;
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
        this.studentId = studentId;
    }

    public Integer getApplication_id() {
        return applicationId;
    }

    public void setApplication_id(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setStatus(String status) {
    }

    public Integer getStudent_id() {
        return studentId;
    }

    public void setStudent_id(Integer studentId) {
        this.studentId = studentId;
    }
}
