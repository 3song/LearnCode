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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//注册到Spring容器中
@MapperScan(basePackages = "com.pay.*",sqlSessionFactoryRef = "paySqlSessionFactory")
public class PayDataSourceConfig {
   /**
    * @Author 陈磊
    * @Description 配置pay数据库
    * @Date
    * @Param
    * @return
    **/
    @Bean(name = "payDataSource")
    //自定义数据源需要把datasource注入到容器中去
    @ConfigurationProperties(prefix = "spring.datasource.pay")
    //读取application.properties 中的pay datasource信息
    public DataSource userDataSource(){
        return DataSourceBuilder.create().build();
    }
    /*
     * @Author 陈磊
     * @Description pay sql会话工厂
     * @Date
     * @Param
     * @return
     **/
    @Bean(name = "paySqlSessionFactory")
    public SqlSessionFactory paySqlSessionFactory(@Qualifier("payDataSource") DataSource dataSource) throws Exception{
        //paySqlSessionFactory 工厂 引用的是 payDataSource 的数据源
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /*
         * 如果使用mybatis xml   还需写如下代码
         * bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/pay/*.xml"));
         * */
        return bean.getObject();
    }
    /**
     * @Author 陈磊
     * @Description //事务管理 创建pay数据源的事务
     * @Date
     * @Param
     * @return
     **/
    @Bean(name = "payTransactionManager")
    @Qualifier("payTransactionManager")
    public DataSourceTransactionManager payTransactionManager(@Qualifier("payDataSource") DataSource dataSource) throws Exception{
        return new DataSourceTransactionManager(dataSource);
    }
    /**
     * @Author 陈磊
     * @Description 创建pay数据源模板
     * @Date
     * @Param
     * @return
     **/
    @Bean("paySqlSessionTemplate")
    public SqlSessionTemplate paySqlSessionTemplate(@Qualifier("paySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
