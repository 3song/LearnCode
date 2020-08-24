package com.api.service;

import com.UsersEntity;
import com.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IMemberService {
    @RequestMapping("/getMember")
    public UsersEntity getMember(@RequestParam(value = "name") String name,@RequestParam(value = "age")  Integer age);

    @RequestMapping("/getUserInfo")
    public ResponseBase getUserInfo();
}
