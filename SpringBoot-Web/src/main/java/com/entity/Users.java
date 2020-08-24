package com.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Users {
    private String id;
    private String username;
    private String password;
    private Integer age;

    public Users() {
        this.id=UUID.randomUUID().toString().replace("-", "");
    }

    public Users(String username, String password) {
        this.id= UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    public Users(String username, String password, Integer age) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
