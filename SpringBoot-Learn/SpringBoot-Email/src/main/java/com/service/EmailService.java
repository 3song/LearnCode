package com.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    @Resource
    private JavaMailSenderImpl javaMailSender;
    //简单邮件测试
    public void sendSimple(String title,String content,String toEmail,String fromEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(content);
        message.setTo(toEmail);
        message.setFrom(fromEmail);
        javaMailSender.send(message);
    }
    //复杂邮件测试
    public void sendComplicated() throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //用MimeMessageHelper来包装MimeMessage
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setSubject("email测试");
        mimeMessageHelper.setText("邮件测试内容");
        mimeMessageHelper.setTo("fanqixxxx@vip.qq.com");
        mimeMessageHelper.setFrom("fanqixxxx@163.com");
        mimeMessageHelper.addAttachment("meinv.jpg",new File("D:\\meinv.jpg"));
        javaMailSender.send(mimeMessage);

    }
}