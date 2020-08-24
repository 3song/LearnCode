package com.service;

import com.pojo.Users;

import java.util.List;


public interface UserService {
    public int insert(Users users);
    public List<Users> findByName(Users users);
}
