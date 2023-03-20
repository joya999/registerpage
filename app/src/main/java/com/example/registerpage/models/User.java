package com.example.registerpage.models;

public class User {

    private Long academicNumber;
    private String email;
    private String name;

    public User(){}
    public void setAcademicNumber(Long academicNumber) {
        this.academicNumber = academicNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }




    public User(Long academicNumber, String email, String name) {
        this.academicNumber = academicNumber;
        this.email = email;
        this.name = name;
    }

    public Long getAcademicNumber() {
        return academicNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
