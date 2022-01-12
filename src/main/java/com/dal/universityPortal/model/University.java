package com.dal.universityPortal.model;

public class University {

    private int userId;
    private String universityName;
    private String universityDescription;

    public University(){
    }

    public University(int userId, String universityName, String universityDescription) {
        this.userId = userId;
        this.universityName = universityName;
        this.universityDescription = universityDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityDescription() {
        return universityDescription;
    }

    public void setUniversityDescription(String universityDescription) {
        this.universityDescription = universityDescription;
    }
}
