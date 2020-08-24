package com.more.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 生产者  交换机类型为ROUTING类型
public class Producer {
    //交换机名称
    private static final String DESTINATION="MY_TOPIC_DESTINATION";
    //topic 模式下 可以使用*和# 来匹配词语
    private static final String ROUTING_KEY="LOG.SMS.EMAIL";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.建立mq连接
        Connection connection = RabbitMQUtils.newConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.生产者绑定交换机 第二个参数交换机类型
        channel.exchangeDeclare(DESTINATION, "topic");
        //4.创建消息
        String msg="MY_TOPIC_DESTINATION_MSG"+ROUTING_KEY;
        //5.发送消息 到交换机 DESTINATION 中去
        channel.basicPublish(DESTINATION, ROUTING_KEY, null, msg.getBytes());
        System.out.println("生产者发送消息，RoutingKey："+ROUTING_KEY);
        //6.关闭通道和连接
        channel.close();
        connection.close();
    }
}
