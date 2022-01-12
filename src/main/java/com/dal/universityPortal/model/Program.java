package com.dal.universityPortal.model;

public class Program extends University {

    private int id;
    private String name;
    private int universityId;
    private int amount;

    public Program() {
        super();
    }

    public Program(int id, String name, int universityId) {
        this.id = id;
        this.name = name;
        this.universityId = universityId;
    }

    public Program(String name, int universityId) {
        this.name = name;
        this.universityId = universityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
