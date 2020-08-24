package com.msg.sms;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class SmsConsumer {
    @RabbitListener(queues = "FANOUT_SMS_QUEUE")
    public void process(String msg){
        System.out.println("短信消费者获取生产者消息并根据信息发送短信");

    }
}
