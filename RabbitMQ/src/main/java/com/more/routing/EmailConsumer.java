package com.more.routing;

import com.rabbitmq.client.*;
import com.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//邮件消费者
public class EmailConsumer {
    private static final String EMAIL_QUEUE="EMAIL_QUEUE_ROUTING";
    //交换机名称
    private static final String DESTINATION="MY_ROUTING_DESTINATION";
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("邮件消费者启动。。。。。。。。");
        //1.建立MQ连接
        Connection connection = RabbitMQUtils.newConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.声明一个队列
        channel.queueDeclare(EMAIL_QUEUE, false, false, false, null);
        //4.消费者队列绑定交换机  绑定路由件
        channel.queueBind(EMAIL_QUEUE, DESTINATION, "EMAIL");
        //5.消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("Email消费者获取生产者消息"+msg);
            }
        };
        //监听生产者发送消息
        channel.basicConsume(EMAIL_QUEUE, true, defaultConsumer);
    }

}
