package com.entity;

public class Users {
    private String id;
    private String name;

    public Users() {
    }

    public Users(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
