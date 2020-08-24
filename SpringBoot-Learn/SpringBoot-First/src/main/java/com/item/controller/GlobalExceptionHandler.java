package com.item.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//表示全局捕获异常
//advice 切面
@EnableAutoConfiguration
@ControllerAdvice
public class GlobalExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)//表示拦截运行时异常
    @ResponseBody
    public Map<String,Object> resultError(){
        Map<String,Object> result =  new HashMap<String, Object>();
        result.put("errorCode","500");
        result.put("errorMsg","系统错误");
        return result;
    }
}
