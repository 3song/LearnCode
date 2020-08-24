package com;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class AppDubboMember {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext pathXmlApplicationContext = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        //pathXmlApplicationContext.getBean("");
        pathXmlApplicationContext.start();
        System.out.println("会员服务启动成功");
        System.in.read();//保持服务一直在运行
    }
}
