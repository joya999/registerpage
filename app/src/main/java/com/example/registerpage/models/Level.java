package com.example.registerpage.models;

public class Level {

    private int id;
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Level(){}

    public Level(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
