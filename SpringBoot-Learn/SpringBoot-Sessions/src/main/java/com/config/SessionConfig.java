package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;

//spring session过期时间
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1000)
@Component
public class SessionConfig {
    @Value("${redis.hostNome}")
    private String hostName;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
//老版本
    @Bean
    public JedisConnectionFactory connectionFactory(){
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory();
        connectionFactory.setHostName(hostName);
        connectionFactory.setPort(port);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
