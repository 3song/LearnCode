package com.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.service.MemberService;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
@SuppressWarnings("rawtypes")
public class OrderHystrixCommandSemaphore extends HystrixCommand<JSONObject> {

        @Resource
        private MemberService memberService;

        /**
         * 分组
         */
        public OrderHystrixCommandSemaphore(MemberService memberService) {
            super(setter());
            this.memberService = memberService;
        }

        protected JSONObject run() throws Exception {

            // Thread.sleep(500);
            // System.out.println("orderIndex线程名称" +
            // Thread.currentThread().getName());
            // System.out.println("success");
            JSONObject member = memberService.getMember();
            System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
            return member;
        }

        private static HystrixCommand.Setter setter() {
            // 服务分组
            HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("members");
            // 命令属性配置 采用信号量模式
            HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                    // 使用一个原子计数器（或信号量）来记录当前有多少个线程在运行，当请求进来时先判断计数
                    // 器的数值，若超过设置的最大线程个数则拒绝该请求，若不超过则通行，这时候计数器+1，请求返 回成功后计数器-1。
                    .withExecutionIsolationSemaphoreMaxConcurrentRequests(50);
            return HystrixCommand.Setter.withGroupKey(groupKey).andCommandPropertiesDefaults(commandProperties);
        }

        @Override
        protected JSONObject getFallback() {
            // 如果Hystrix发生熔断，当前服务不可用,直接执行Fallback方法
            System.out.println("系统错误！");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "系统错误！");
            return jsonObject;
        }


}

