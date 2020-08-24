package com.entity;

public class Users {
    private Integer id;
    private String name;
    private Integer age;

    public Users() {
    }

    public Users(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
