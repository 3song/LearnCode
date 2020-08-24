package com.controller;

import com.rabbitmq.producer.FanoutProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {
    @Resource
    FanoutProducer fanoutProducer;
    @RequestMapping("/sendMsg")
    public String sendMsg(String queueName){
        fanoutProducer.send(queueName);
        return "发送消息给消费者";
    }
}
