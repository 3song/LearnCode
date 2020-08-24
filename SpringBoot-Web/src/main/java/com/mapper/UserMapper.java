package com.mapper;

import com.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
    //@Select("select id,username,password,age from User where username=#{users.username} and password=#{users.password}")
    @Select("select id,username,password,age from User where username='${users.username}' and password='${users.password}'")
    public Users getUserByPassword(@Param("users") Users users);

    @Insert("insert into User (id,username,password,age) values (#{users.id},#{users.username},#{users.password},#{users.age})")
    public int insertUser(@Param("users") Users users);
}
