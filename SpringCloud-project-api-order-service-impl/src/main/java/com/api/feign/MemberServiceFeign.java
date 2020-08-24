package com.api.feign;

import com.api.fallback.MemberServiceFeignFallback;
import com.api.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "app-3song-member",fallback = MemberServiceFeignFallback.class)
public interface MemberServiceFeign extends IMemberService {
    //继承 IMemberService 就不需要重写接口的方法
    //@RequestMapping("/getMember")
    //public UsersEntity getMember(String name,Integer age);
}
