package com;

import com.config.ClassPathXMLApplicationContext;
import com.entity.Users;
import org.dom4j.DocumentException;

public class AppSpring {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, DocumentException, IllegalAccessException {
        ClassPathXMLApplicationContext classPathXMLApplicationContext=new ClassPathXMLApplicationContext("application.xml");
        Users users1 = (Users) classPathXMLApplicationContext.getBean("users1");//
        Users users2 = (Users) classPathXMLApplicationContext.getBean("users2");//
        System.out.println(users1.toString());
        System.out.println(users2.toString());
    }
}
