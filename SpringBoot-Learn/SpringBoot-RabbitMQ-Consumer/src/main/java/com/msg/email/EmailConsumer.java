package com.msg.email;

import com.alibaba.fastjson.JSONObject;
import com.msg.utils.HttpClientUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmailConsumer {
   /*
    //如果消费端程序业务逻辑出现异常，消息会不会消费成功？ 如todo
    // so SpringBoot 会重试
    @RabbitHandler
    // 或者写@RabbitListener(queues = "FANOUT_EMAIL_QUEUE")
    public void process(String msg) throws Exception {
        System.out.println("邮件消费者获取生产者消息并根据信息发送邮件"+msg);
//        JSONObject jsonObject = JSONObject.parseObject(msg);
//        jsonObject.getString("email");
        //  int i=1/0; TODO
        String sendEmailUrl="http://192.168.50.73:8083/sendEmail";
        System.out.println("邮件消费者开始调用第三方邮件服务器：sendEmailUrl为"+sendEmailUrl);
        JSONObject result = HttpClientUtils.httpGet(sendEmailUrl);
        System.out.println(result);
        //如果调用邮件接口时无法访问，如何重试
        if (result==null){
            //重试 使用SpringAOP 拦截异常
            throw new Exception("网络访问不通，重试请求");
        }
        System.out.println("邮件消费者调用第三方邮件服务器成功：result为"+result+"程序执行结束");
    }
   */
    @RabbitListener(queues = "FANOUT_EMAIL_QUEUE")
    // 或者写@RabbitListener(queues = "FANOUT_EMAIL_QUEUE")
    public void process(Message message, @Headers Map<String,Object> headers, Channel channel) throws Exception {
        String messageId=message.getMessageProperties().getMessageId();
        String msg=new String(message.getBody(),"UTF-8");
        System.out.println("邮件消费者获取生产者消息并根据信息发送邮件"+msg+"，获取的消息id为："+messageId);
//        JSONObject jsonObject = JSONObject.parseObject(msg);
//        jsonObject.getString("email");
        //  int i=1/0; TODO
        String sendEmailUrl="http://192.168.50.73:8083/sendEmail";
        System.out.println("邮件消费者开始调用第三方邮件服务器：sendEmailUrl为"+sendEmailUrl);
        JSONObject result = null;
        try {
            int i=1/0;//模拟报错丢失消息
            result = HttpClientUtils.httpGet(sendEmailUrl);
        } catch (Exception e) {
            //拒绝消费消息（丢失消息）
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
            System.out.println("此消息已经手动丢失，转发给死信队列");
            e.printStackTrace();
        }
        //如果调用邮件接口时无法访问，如何重试
        if (result==null){
            //重试 使用SpringAOP 拦截异常
            throw new Exception("网络访问不通，重试请求");
        }
        System.out.println("邮件消费者调用第三方邮件服务器成功：result为"+result+"程序执行结束");
        // 由于配置文件修改为手动应答模式，所以需要手写返回Ack包代码
        //手动创建Ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
        channel.basicAck(deliveryTag, false);

    }
    // MQ默认5秒重试1次

    // MQ重试机制需要注意消息的幂等性问题
    // MQ消费者幂等性问题如何解决，使用全局MessageID
    //比如上方是一个邮件发送的消费者，在做补偿时，假如上一步邮件发送成功了，我们会把该 ID 存至 redis中，下次再调用时，先去 redis 判断是否存在该 ID 了，如果存在表明已经消费过了则直接返回，不再消费，否则消费，然后将记录存至 redis。
}
