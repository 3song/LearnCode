package com.hystrix;

import com.netflix.hystrix.*;
import com.service.MemberService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class OrderHystrixCommand extends HystrixCommand<JSONObject> {
     private MemberService memberService;

     //JsonObject 表示服务返回类型
     public OrderHystrixCommand(MemberService memberService){
          //类加载时需要加载配置Hystrix 的setter方法
          super(setter());
          this.memberService=memberService;
     }
     private static Setter setter() {

          // 服务分组
          HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("members");
          // 服务标识
          HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("member");
          // 线程池名称 为什么要配置线程池名称 因为每个服务都需要有独立的线程池
          HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("member-pool");
          // #####################################################
          // 线程池配置 线程池大小为10,线程存活时间15秒 队列等待的阈值为100,超过100情况下服务会进行熔断，执行fallback方法，实现服务降级，执行拒绝策略
          HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(10)
                  .withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);
          // ########################################################
          // 命令属性配置Hystrix 开启超时
          HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                  // 采用线程池方式实现服务隔离 HystrixCommandProperties.ExecutionIsolationStrategy.THREAD
                  .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                  // 禁止超时
                 .withExecutionTimeoutEnabled(false);
          return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
                  .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

     }

     //服务降级方法
     @Override
     public JSONObject getFallback(){
          JSONObject jsonObject=new JSONObject();
          jsonObject.put("code", "500");
          jsonObject.put("msg", "服务器忙碌，进行服务降级，请稍后重试");
          return jsonObject;
     }
     //表示服务执行的代码
     @Override
     protected JSONObject run() throws Exception {
          JSONObject member = memberService.getMember();
          System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
          return member;
     }
}
