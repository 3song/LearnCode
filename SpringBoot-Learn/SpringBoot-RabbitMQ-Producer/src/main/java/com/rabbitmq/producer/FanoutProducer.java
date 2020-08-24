package com.rabbitmq.producer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Component
public class FanoutProducer {
    @Resource
    private AmqpTemplate amqpTemplate;
    public void send(String queueName){
        System.out.println("队列名称为："+queueName);
        String msg="msg:"+new Date();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("email", "1048253765");
        jsonObject.put("timestamp", System.currentTimeMillis());
        String jsonString = jsonObject.toJSONString();
        System.out.println("jsonObject:"+jsonString);
        //为解决消息幂等性问题，生产者发送消息时需要设置全局消息id
        Message build = MessageBuilder.withBody(msg.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("UTF-8").setMessageId(UUID.randomUUID() + "").build();
        //发送消息 TODO 在此可以指定RoutingKey
        amqpTemplate.convertAndSend(queueName,build);
        //amqpTemplate.convertAndSend(queueName,msg); 通过发送的消息类型来区分回调方法
    }
}
