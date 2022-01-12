package com.dal.universityPortal.database.query;

public class DashboardQuery {

    public static final String APPLICATIONS_FROM_STUDENT_ID = "SELECT * from application where student_id = ?";
    public static final String PAYMENT_FROM_STUDENT_ID = "SELECT * from payment where student_id =?";
}
