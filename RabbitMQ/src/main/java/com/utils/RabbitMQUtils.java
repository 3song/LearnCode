package com.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    public static Connection newConnection() throws IOException, TimeoutException {
        // 1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2.设置连接地址
        connectionFactory.setHost("192.168.50.73");
        // 3.设置用户名称和用户密码
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("211716");
        // 4.设置AMQP端口号
        connectionFactory.setPort(5672);
        // 5.设置VirtualHost地址
        connectionFactory.setVirtualHost("/admin_host");
        return connectionFactory.newConnection();
    }
}
