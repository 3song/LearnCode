package com.pay.mapper;

import com.pojo.Users;
import com.pojo.UsersExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional(rollbackFor=Exception.class)
public interface PayMapper {
    @SelectProvider(type= PaySqlProvider.class, method="countByExample")
    long countByExample(UsersExample example);

    @DeleteProvider(type= PaySqlProvider.class, method="deleteByExample")
    int deleteByExample(UsersExample example);

    @Delete({
        "delete from users",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);
    /*@Insert({
        "insert into users (id, name, ",
        "age)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER})"
    })*/
    @Insert({
        "insert into users ( name, ",
        "age)",
        "values ( #{name,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER})"
    })
    //此处id 设置为自增长
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insert(Users record);

    @InsertProvider(type= PaySqlProvider.class, method="insertSelective")
    int insertSelective(Users record);

    @SelectProvider(type= PaySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER)
    })
    List<Users> selectByExample(UsersExample example);

    @Select({
        "select",
        "id, name, age",
        "from users",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER)
    })
    Users selectByPrimaryKey(Integer id);

    @UpdateProvider(type= PaySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    @UpdateProvider(type= PaySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    @UpdateProvider(type= PaySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Users record);

    @Update({
        "update users",
        "set name = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Users record);
    //自定义findByName方法
    @Select({
            "select",
            "id, name, age",
            "from users",
            "where name = #{name,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Users> findByName(Users record);
}