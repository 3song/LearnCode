package com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "app-vip-member")
public interface MemberApiFeign {
    @RequestMapping("/getMember")
    public String getMember();
}
