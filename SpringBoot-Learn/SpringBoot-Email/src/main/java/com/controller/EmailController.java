package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.service.EmailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {
    @Resource
    private EmailService emailService;
    @RequestMapping("/sendEmail")
    @ResponseBody
    public JSONObject sendEmail(){
        String title="测试邮件标题";
        String content="测试邮件内容";
        String toEmail="songs3songs@163.com";
        String fromEmail="1048253765@qq.com";
        emailService.sendSimple(title, content, toEmail, fromEmail);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("data", "success");
        jsonObject.put("msg", "邮件发送成功");
        return jsonObject;
    }
}
