package com.api.service.impl;

import com.UsersEntity;
import com.api.service.IMemberService;
import com.base.BaseApiService;
import com.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {
    @Override
    @RequestMapping("/getMember")
    public UsersEntity getMember(@RequestParam(value = "name") String name,@RequestParam(value = "age")  Integer age) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setName(name);
        usersEntity.setAge(age);
        System.out.println("进入getMemberImpl成功");
        return usersEntity;
    }

    @Override
    @RequestMapping("/getUserInfo")
    public ResponseBase getUserInfo() {
        //1.5秒 是为了测试Hystrix服务保护时间
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return setResultSuccess(" 1.5s 订单接口调用会员服务接口成功.................");
    }
}
