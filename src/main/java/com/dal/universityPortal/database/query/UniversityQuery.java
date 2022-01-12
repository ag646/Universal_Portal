package com.dal.universityPortal.database.query;

public class UniversityQuery {
    public static final String FETCH_ALL_UNIVERSITIES = "SELECT * from university";
    public static final String INSERT_UNIVERSITY = "INSERT INTO university (user_id, university_name, " +
            "university_description) VALUES (?,?,?)";

}
