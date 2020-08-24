package com.dao;

import com.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UsersEntity,Integer> {
    //继承之后  就可以用jpa内置的方法，无需重写
}
