/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.controller;

import com.hystrix.OrderHystrixCommand;
import com.service.MemberService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年6月30日 下午12:45:16<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	@Resource
	private MemberService memberService;

	//订单服务调用会员服务 解决服务雪崩效应，底层使用服务隔离 线程池方式实现
	@RequestMapping("/orderIndexHystrix")
	public Object orderIndexHystrix() throws InterruptedException {
		//原来的方法内容需要写到 OrderHystrixCommand 中的run() 方法中去，让OrderHystrixCommand 实现服务隔离与降级
		//通过服务隔离   来创建一个新的线程池
		JSONObject jsonObject = new OrderHystrixCommand(memberService).execute();
		return jsonObject;
	}

	@RequestMapping("/orderIndex")
	public Object orderIndex() throws InterruptedException {
		JSONObject member = memberService.getMember();
		System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
		return member;
	}

	@RequestMapping("/findOrderIndex")
	public Object findIndex() {
		System.out.println("当前线程:" + Thread.currentThread().getName() + ",findOrderIndex");
		return "findOrderIndex";
	}
}
