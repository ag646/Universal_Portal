package com.dal.universityPortal.database.query;

public class PaymentQuery {

    public static final String FOREIGN_KEY_CHECKS = "SET FOREIGN_KEY_CHECKS=OFF;";
    public static final String FETCH_STATUS_APPLICATION_ID = "SELECT status from application where id = ?";
    public static final String UPDATE_STATUS_APPLICATION_ID = "UPDATE application SET status = 'New' WHERE id = ?";
    public static final String INSERT_INTO_PAYMENTS = "INSERT INTO payment(application_id, cardNumber, amount, status, student_id)" + "VALUES (?,?,?,?,?)";
    public static final String UPDATE_STATUS_PAYMENTS = "UPDATE payment SET status = 'PAID' where application_id = ?";
}
