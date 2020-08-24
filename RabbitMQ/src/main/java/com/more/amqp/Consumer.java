package com.more.amqp;

import com.rabbitmq.client.*;
import com.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    //队列名称
    private static final String QUEUE_NAME="3SONG";
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建新链接
        Connection connection = RabbitMQUtils.newConnection();
        //2.创建通道(AMQP原理)
        Channel channel = connection.createChannel();
        //3.消费者关联一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //4.监听队列消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            //监听获取消息
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String s = new String(body, "UTF-8");
                System.out.println("消费者获取生产者消息："+s);
                //channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 设置应答模式（签收）默认自动应答 第二个参数为true表示自动应答 false 表示手动应答
        channel.basicConsume(QUEUE_NAME, true,defaultConsumer);
        //6.关闭通道和连接 （消费者需要监听 不能关闭连接）
//        channel.close();
//        connection.close();
    }
}
