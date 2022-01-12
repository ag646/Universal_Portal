package com.dal.universityPortal.database.query;

public class UserQuery {

    public static final String INSERT_USER_QUERY = "INSERT INTO user (username, email, password, type, status) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static final String FETCH_USER_USING_USERNAME = "SELECT * FROM user WHERE username=?";
    public static final String UPDATE_USER_QUERY = "UPDATE user SET username=?, email=?, password=?, type=?, status=? WHERE id=?";
    public static final String SET_USER_RESET_CODE = "UPDATE user SET reset_code=? WHERE id=?";
    public static final String MAP_STAFF_USER_WITH_UNIVERSITY_USER = "INSERT INTO staff (id, university_id) VALUES (?, ?)";
    public static final String FETCH_PENDING_USERS_QUERY = "SELECT * FROM user WHERE status = ?";
    public static final String UPDATE_USER_STATUS = "UPDATE user SET status = ? WHERE id = ?";
}
