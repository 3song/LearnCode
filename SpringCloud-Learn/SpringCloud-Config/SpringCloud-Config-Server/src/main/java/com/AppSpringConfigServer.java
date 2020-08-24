package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
@EnableConfigServer
public class AppSpringConfigServer {
    /*
     * @Author 陈磊
     * @Description //TODO
     * @Date
     * @Param
     * @return
     * 1.在git环境上创建配置文件名称规范
     * 服务名称-环境.properties/yml
     **/
    public static void main(String[] args) {
        SpringApplication.run(AppSpringConfigServer.class, args);
    }
}
