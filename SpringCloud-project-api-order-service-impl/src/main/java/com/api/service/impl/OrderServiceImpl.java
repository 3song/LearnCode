package com.api.service.impl;

import com.UsersEntity;
import com.api.feign.MemberServiceFeign;
import com.api.service.IOrderService;
import com.base.BaseApiService;
import com.base.ResponseBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderServiceImpl extends BaseApiService implements IOrderService {
    @Resource
    private MemberServiceFeign memberServiceFeign;
    @Override
    @RequestMapping("/orderToMember")
    public String orderToMember(String name, Integer age) {
        UsersEntity users = memberServiceFeign.getMember(name, age);
        return users==null?"没有找到用户信息,请输入参数！！！":users.toString();
    }

    //没有解决服务雪崩效应
    @Override
    @RequestMapping("/orderToMemberUserInfo")
    public ResponseBase orderToMemberUserInfo() {
        return memberServiceFeign.getUserInfo();
    }

    @RequestMapping("/orderToMemberUserInfoHystrix")
    // fallbackMethod作用是服务降级执行
    @HystrixCommand(fallbackMethod = "orderToMemberUserInfoHystrixFallbackMethod")
    public ResponseBase orderToMemberUserInfoHystrix() {
        //HystrixCommand 默认开启线程池隔离模式,和服务降级，服务熔断机制
        System.out.println("orderToMemberUserInfoHystrix:"+"线程池名称："+Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }

    //服务降级方法
    public ResponseBase orderToMemberUserInfoHystrixFallbackMethod() {
        return setResultSuccess("返回一个友好提示，服务降级：服务器繁忙请稍后重试");
    }

    @RequestMapping("/orderToMemberUserInfoHystrixClass")
    // 第二种服务降级方法 MemberServiceFeign,指定fallback Class
    public ResponseBase orderToMemberUserInfoHystrixClass() {
        System.out.println("orderToMemberUserInfoHystrixClass:" + "线程池名称：" + Thread.currentThread().getName());
        return memberServiceFeign.getUserInfo();
    }
    //订单服务接口
    @Override
    @RequestMapping("/orderInfo")
    public ResponseBase orderInfo() {
        System.out.println("orderInfo:"+"线程池名称："+Thread.currentThread().getName());
        return setResultSuccess();
    }

    //Hystrix 有两种方式配置保护服务 1.通过注解 2.通过接口


}
