package com.api.service;

import com.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IOrderService {
    @RequestMapping("/orderToMember")
    public String orderToMember(@RequestParam String name,@RequestParam Integer age);

    @RequestMapping("/orderToMemberUserInfo")
    public ResponseBase orderToMemberUserInfo();

    //订单服务接口
    @RequestMapping("/orderInfo")
    public ResponseBase orderInfo();
}
