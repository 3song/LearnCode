package com.feign;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignController {
    @Resource
    private MemberApiFeign memberApiFeign;
    @RequestMapping("/feignMember")
    public String feignMember(){
        //使用feign客户端调用 接口
       return memberApiFeign.getMember();
    }
}
