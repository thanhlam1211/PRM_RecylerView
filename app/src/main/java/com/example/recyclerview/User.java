package com.example.recyclerview;

public class User {
    private int resource;
    private String name;

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int resource, String name) {
        this.resource = resource;
        this.name = name;
    }
}
