package com.example.registerpage.models;

public class Subject {

    public String code;
    public String name;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Subject(){}
}
