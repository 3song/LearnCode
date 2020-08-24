package com.mapper;

import com.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
//表示配置缓存信息
@CacheConfig(cacheNames={"userCache"})
@Mapper
public interface UsersMapper {
    @Insert("insert into Users(name,age) values(#{users.name},#{users.age})")
    public int insertUser(@Param("users") Users users);

    //配置此方法使用缓存 该方法查询数据库完毕后，存入到缓存中
    @Cacheable
    @Select("select id,name,age from Users where id=#{id}")
    public List<Users> getUserById(@Param("id") Integer id);
}
