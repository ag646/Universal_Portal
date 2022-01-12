package com.dal.universityPortal.model;

public class Dashboard {

    private int userId;
    private int successfulApplication;
    private int inProgressApplication;
    private int rejectedApplication;
    private int totalPayment;

    public Dashboard() {
    }

    public Dashboard(int userId, int successfulApplication, int inProgressApplication, int rejectedApplication, int totalPayment) {
        this.userId = userId;
        this.successfulApplication = successfulApplication;
        this.inProgressApplication = inProgressApplication;
        this.rejectedApplication = rejectedApplication;
        this.totalPayment = totalPayment;
    }

    public int getUserId() {
        return userId;
    }

    public int getSuccessfulApplication() {
        return successfulApplication;
    }

    public int getInProgressApplication() {
        return inProgressApplication;
    }

    public int getRejectedApplication() {
        return rejectedApplication;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void incrementSuccessfulApplications() {
        successfulApplication++;
    }

    public void incrementInProcessApplications() {
        inProgressApplication++;
    }

    public void incrementRejectedApplications() {
        rejectedApplication++;
    }

    public void appPayment(Integer amount) {
        totalPayment = totalPayment + amount;
    }
}
