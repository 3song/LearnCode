package com.api.fallback;

import com.UsersEntity;
import com.api.feign.MemberServiceFeign;
import com.base.BaseApiService;
import com.base.ResponseBase;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceFeignFallback extends BaseApiService implements MemberServiceFeign {
    //开启独立线程池执行
    @Override
    public UsersEntity getMember(String name, Integer age) {
        return null;
    }

    @Override
    public ResponseBase getUserInfo() {
        return setResultError("服务器忙，请稍后重试,以类方式写服务降级");
    }
}
