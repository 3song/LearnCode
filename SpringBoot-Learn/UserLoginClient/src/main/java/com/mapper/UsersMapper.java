package com.mapper;

import com.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UsersMapper {
    @Insert("insert into users values(#{name},#{age})")
    public int insert(Users users);

    @Select("select id,name,age from users")
    public List<Users> findAll();

    @Select("select id,name,age from users where name=#{name} and age=#{age}")
    public Users findByNameAndAge(Users users);

}
