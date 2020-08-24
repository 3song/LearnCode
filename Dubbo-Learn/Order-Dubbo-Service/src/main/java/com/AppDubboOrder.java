package com;

import com.lock.api.member.MemberService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppDubboOrder {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext pathXmlApplicationContext = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        MemberService memberService = pathXmlApplicationContext.getBean(MemberService.class);
        String user = memberService.getUser(211716);
        System.out.println("订单服务调用会员服务返回结果："+user);

    }
}
