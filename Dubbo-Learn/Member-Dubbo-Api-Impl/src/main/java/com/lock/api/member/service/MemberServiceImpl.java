package com.lock.api.member.service;

import com.lock.api.member.MemberService;

public class MemberServiceImpl implements MemberService {
    private String serverPort="20800";
    @Override
    public String getUser(Integer id) {
        System.out.println("订单服务调用会员服务：userId:"+id);
        return "ChenLei";
    }
}
