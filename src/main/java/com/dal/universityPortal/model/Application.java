package com.dal.universityPortal.model;

public class Application {

    private int application_id;
    private int program_id;
    private int student_id;
    private String status;
    private String sop;
    private int processed_by;
    private String comment;

    private String first_name;
    private String last_name;
    private String address;
    private String mobile_number;
    private String email_id;

    private String highest_education;
    private String grades;
    private String start_date;
    private String end_date;

    public Application(){
    }

    public Application(int application_id, int program_id, int student_id, String status, int processed_by, String comment, String first_name, String last_name, String address, String mobile_number, String email_id, String highest_education, String grades, String start_date, String end_date, String sop) {
        this.application_id = application_id;
        this.program_id = program_id;
        this.student_id = student_id;
        this.status = status;
        this.processed_by = processed_by;
        this.comment = comment;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.mobile_number = mobile_number;
        this.email_id = email_id;
        this.highest_education = highest_education;
        this.grades = grades;
        this.start_date = start_date;
        this.end_date = end_date;
        this.sop = sop;
    }

    public int getApplication_id() { return application_id; }

    public void setApplication_id(int application_id) { this.application_id = application_id; }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getHighest_education() {
        return highest_education;
    }

    public void setHighest_education(String highest_education) {
        this.highest_education = highest_education;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getSop() {
        return sop;
    }

    public void setSop(String sop) {
        this.sop = sop;
    }

    public int getProgram_id() { return program_id; }

    public void setProgram_id(int program_id) { this.program_id = program_id; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public int getProcessed_by() { return processed_by; }

    public void setProcessed_by(int processed_by) { this.processed_by = processed_by; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public int getStudent_id() { return student_id; }

    public void setStudent_id(int student_id) { this.student_id = student_id; }
}
