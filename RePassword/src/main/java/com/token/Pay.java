package com.token;

import com.token.base.BaseApiService;
import com.token.base.ResponseBase;
import com.token.utils.BaseRedisService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
@RestController
public class Pay extends BaseApiService {

    @Resource
    private BaseRedisService baseRedisService;

    private static final Long TOKEN_TIME=(long) (30*60);
    @RequestMapping("/getPayToken")
    public String getPayToken(String id,Long money){
        //生成令牌
        String payToken= UUID.randomUUID().toString();
        baseRedisService.setString(payToken, id+"==="+money,TOKEN_TIME);
        return payToken;
    }
    @RequestMapping("/pay")
    public ResponseBase pay(String payToken){
        if (StringUtils.isEmpty(payToken)){
            return setResultError("token 不能为空");
        }
        String param = (String) baseRedisService.getString(payToken);
        if (StringUtils.isEmpty(param)){
            return setResultError("param不能为空或找不到对应令牌信息");
        }
        //操作数据库
        return setResultSuccess(param);
    }
}
