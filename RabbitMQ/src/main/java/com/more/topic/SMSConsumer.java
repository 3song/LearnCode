package com.more.topic;

import com.rabbitmq.client.*;
import com.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//邮件消费者
public class SMSConsumer {
    private static final String SMS_QUEUE="SMS_QUEUE_TOPIC";
    //交换机名称
    private static final String DESTINATION="MY_TOPIC_DESTINATION";
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("短信消费者启动。。。。。。。。");
        //1.建立MQ连接
        Connection connection = RabbitMQUtils.newConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.声明一个队列
        channel.queueDeclare(SMS_QUEUE, false, false, false, null);
        //4.消费者队列绑定交换机并配置routingKey
        channel.queueBind(SMS_QUEUE, DESTINATION, "LOG.#");
        //可以加多个
//        channel.queueBind(SMS_QUEUE, DESTINATION, "EMAIL");
        //5.消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("SMS消费者获取生产者消息"+msg);
            }
        };
        //监听生产者发送消息
        channel.basicConsume(SMS_QUEUE, true, defaultConsumer);
    }

}
