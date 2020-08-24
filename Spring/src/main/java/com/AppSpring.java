package com;

import com.dao.UserDao;
import com.entity.Users;
import com.service.UserService;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("application.xml");
        /*Users users1 = (Users) applicationContext.getBean("users1");
        users1.setName("陈磊");
        users1.setAge(25);
        System.out.println(users1.toString());*/
        //Spring 转换Bean名称 默认首字母小写
        UserService userService= (UserService) applicationContext.getBean("userService");
        userService.add();
//        UserDao userDao= (UserDao) applicationContext.getBean("userDao");
//        userDao.add("",0);
        //BeanFactory
        //BeanDefinition
    }
}
