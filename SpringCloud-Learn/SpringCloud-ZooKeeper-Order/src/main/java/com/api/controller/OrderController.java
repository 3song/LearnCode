package com.api.controller;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderController {
    private static Logger logger=Logger.getLogger("info");
    //1.使用HttpClient 调用接口
    //2.在SpringCloud中 有两种方式调用 rest方式（rest方式是由SpringBootWeb组件提供）和feign方式(feign方式是SpringCloud自带的) 两种方式底层都是使用HttpClient调用的
    // RestTemplate 默认整合了ribbon负载均衡器 rest方式是采用了HttpClient技术
    @Resource
    private RestTemplate restTemplate;
    //使用此api获取注册中心服务的列表信息
    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getOrder")
    public String getOrder(){
        //两种方式，一种是采用服务别名方式调用，另一种是直接调用
        //1.http://192.168.50.73:8082/getMember
        String url="http://192.168.50.73:8082/getMember";
        String result = restTemplate.getForObject(url, String.class);
        logger.info("订单服务调用会员服务结果"+result);
        return result;
    }

    @RequestMapping("/getOrderServiceName")
    public String getOrderServiceName(){
        //2.使用别名去注册中心上获取对应的调用地址 （在yml中定义的：app-vip-member）
        String url="http://app-com-zookeeper/getMember";
        String result = restTemplate.getForObject(url, String.class);
        logger.info("订单服务调用会员服务结果"+result);
        return result;
        // I/O error on GET request for "http://app-vip-member/getMember": app-vip-member; nested exception is java.net.UnknownHostException: app-vip-member
    }

    //如何获取到 注册中心上服务列表的信息
    @RequestMapping("/discoveryClientOrders")
    public List<ServiceInstance> discoveryClientOrders(){
        logger.info("进入discoveryClientOrders");
        //使用集合原因是注册中心可能为多个
        List<ServiceInstance> instances = discoveryClient.getInstances("app-com-zookeeper-order");
        for (ServiceInstance instance:instances){
            logger.info("服务URL信息为:"+String.valueOf(instance.getUri()));
        }
        return instances;
    }



}
