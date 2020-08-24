package com.more.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    //队列名称
    private static final String QUEUE_NAME="3SONG";
    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建新链接
        Connection connection = RabbitMQUtils.newConnection();
        //2.创建通道(AMQP原理)
        Channel channel = connection.createChannel();
        //3.创建一个队列
        channel.basicQos(1);//表示必须将多少条消息消费完才会发送
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        try {
            //开启事务
            channel.txSelect();
            //4.创建一个消息
            String msg="3SONG_MSG";
            System.out.println("生产者投递消息内容"+msg);
            //5.生产者发送消息给消费者
            for (int i = 0; i < 5; i++) {
                channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            }
            int i=1/0;
            channel.txCommit();//提交事务
        } catch (IOException e) {
            System.out.println("报错，进行消息回滚");
            channel.txRollback();
            e.printStackTrace();
        }finally {
            //6.关闭通道和连接
            channel.close();
            connection.close();
        }
    }
}
