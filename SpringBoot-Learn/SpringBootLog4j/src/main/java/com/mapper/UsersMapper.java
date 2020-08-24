package com.mapper;

import com.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
@CacheConfig(cacheNames = "baseCache")
public interface UsersMapper {
    //myBatis 使用#{name},#{age} 获取属性 与实体类名称相同
    @Insert("INSERT INTO users (name ,age) VALUES ( #{name},#{age} )")
    public int insertUser(Users users);

    @Cacheable
    @Select("select id ,name ,age from  users")
    public List<Users> findAll();
}
