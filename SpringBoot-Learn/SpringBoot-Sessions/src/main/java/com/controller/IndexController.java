package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class IndexController {
    @Value("${server.port}")
    private String PORT;
    @RequestMapping("/")
    public String index(){
        return "index:"+PORT;
    }
    @RequestMapping("/setSession")
    public String setSession(HttpServletRequest request,String sessionKey,String sessionValue){
        //是否每次都创建一个新的session
        HttpSession session=request.getSession(true);
        session.setAttribute(sessionKey, sessionValue);
        return "success,port:"+PORT;
    }

    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request,String sessionKey){
        //是否每次都创建一个新的session
        HttpSession session=request.getSession(true);
        String sessionValue = (String) session.getAttribute(sessionKey);
        return "success,sessionValue:"+sessionValue+"port:"+PORT;
    }
}
