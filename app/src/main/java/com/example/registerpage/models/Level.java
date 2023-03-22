package com.example.registerpage.models;

import java.util.List;

public class Level {

    public int id;
    public String name;
    public String key;
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
