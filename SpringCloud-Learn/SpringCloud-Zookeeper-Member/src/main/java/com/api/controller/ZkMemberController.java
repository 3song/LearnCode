package com.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZkMemberController {
    @Value("${server.port}")
    private String serverPort;
    @RequestMapping("/getMember")
    public String getMember(){
        //  http://127.0.0.1:8082/getMember
        // http://127.0.0.1:8082/actuator/info 是eureka自动生成的
        return "this is 3SONG Member,我是VIP服务。ZooKeeperIp为："+serverPort;
    }

}
