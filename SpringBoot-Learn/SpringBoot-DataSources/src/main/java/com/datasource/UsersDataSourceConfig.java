package com.datasource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
//注册到Spring容器中
@MapperScan(basePackages = "com.users.*",sqlSessionFactoryRef = "usersSqlSessionFactory")
public class UsersDataSourceConfig {
   /**
    * @Author 陈磊
    * @Description 配置users数据库
    * @Date
    * @Param
    * @return
    **/
    @Bean(name = "usersDataSource")
    //自定义数据源需要把datasource注入到容器中去
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.users")
    //读取application.properties 中的users datasource信息
    public DataSource userDataSource(){
        return DataSourceBuilder.create().build();
    }
    /*
     * @Author 陈磊
     * @Description users sql会话工厂
     * @Date
     * @Param
     * @return
     **/
    @Bean(name = "usersSqlSessionFactory")
    @Primary
    public SqlSessionFactory usersSqlSessionFactory(@Qualifier("usersDataSource") DataSource dataSource) throws Exception{
        //usersSqlSessionFactory 工厂 引用的是 usersDataSource 的数据源
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /*
         * 如果使用mybatis xml   还需写如下代码
         * bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/users/*.xml"));
         * */
        return bean.getObject();
    }
    /**
     * @Author 陈磊
     * @Description //事务管理 创建users数据源的事务
     * @Date
     * @Param
     * @return
     **/
    @Bean(name = "usersTransactionManager")
    @Primary
    public DataSourceTransactionManager usersTransactionManager(@Qualifier("usersDataSource") DataSource dataSource) throws Exception{
        return new DataSourceTransactionManager(dataSource);
    }
    /**
     * @Author 陈磊
     * @Description 创建users数据源模板
     * @Date
     * @Param
     * @return
     **/
    @Bean("usersSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate usersSqlSessionTemplate(@Qualifier("usersSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
