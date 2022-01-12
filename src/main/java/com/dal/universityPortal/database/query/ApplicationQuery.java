package com.dal.universityPortal.database.query;

public class ApplicationQuery {
    public static final String FETCH_APPLICATION_BY_ID_QUERY = "SELECT * FROM application WHERE student_id = %s";
    public static final String FETCH_STUDENT_BY_ID_QUERY = "SELECT * FROM student WHERE student_id = ?";
    public static final String FETCH_USER_BY_ID_QUERY = "SELECT * FROM user WHERE id = ?";
    public static final String FETCH_EDUCATION_BY_STUDENT_ID = "SELECT * FROM education WHERE student_id = ?";
    public static final String UPDATE_APPLICATION_BY_APPLICATION_ID = "UPDATE application SET status = ?, " +
            "processed_by = ?, comment = ?  WHERE id = ?";
    public static String FETCH_ALL_APPLICATION = "SELECT * FROM application;";
    public static String FETCH_APPLICATION_BY_ID = "SELECT * FROM application where id=?";
    public static String FETCH_STUDENT_BY_ID="SELECT * FROM student WHERE user_id = ";
    public static String FETCH_USER_BY_ID="SELECT * FROM user WHERE id = ";
    public static String FETCH_EDUCATION_BY_ID="SELECT * FROM education WHERE student_id = ";
    public static String INSERT_APPLICATION="INSERT INTO application (program_id, student_id, sop, " +
            "status, processed_by, comment) VALUE (?,?,?,?,?,?);";
    public static String INSERT_EDUCATION="INSERT INTO education (student_id, name, outcome, " +
            "outcome_type, start_date, end_date) VALUE (?,?,?,?,?,?);";

}
