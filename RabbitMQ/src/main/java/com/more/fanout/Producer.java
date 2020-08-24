package com.more.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// 生产者  交换机类型为Fanout类型
public class Producer {
    //交换机名称
    private static final String DESTINATION="MY_FANOUT_DESTINATION";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.建立mq连接
        Connection connection = RabbitMQUtils.newConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.生产者绑定交换机 第二个参数交换机类型
        channel.exchangeDeclare(DESTINATION,"fanout");
        //4.创建消息
        String msg="MY_FANOUT_DESTINATION_MSG";
        //5.发送消息 到交换机 DESTINATION 中去
        channel.basicPublish(DESTINATION, "", null, msg.getBytes());
        System.out.println("生产者发送了一条消息"+msg);
        //6.关闭通道和连接
        channel.close();
        connection.close();
    }
}
