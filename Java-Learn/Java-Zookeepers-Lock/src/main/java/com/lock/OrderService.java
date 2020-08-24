/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.lock;

import com.lock.api.ExtLock;
import com.lock.api.impl.ZookeeperDistributeLock;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月11日 下午5:35:03<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class OrderService implements Runnable {
	private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
	private ExtLock extLock = new ZookeeperDistributeLock();

	public void run() {
		getNumber();
	}

	public void getNumber() {
		try {
			extLock.getLock();
			String number = orderNumGenerator.getNumber();
			System.out.println("线程:" + Thread.currentThread().getName() + ",生成订单id:" + number);
		} catch (Exception e) {

		} finally {
			extLock.unLock();
		}
	}

	public static void main(String[] args) {
		System.out.println("多线程生成number");
		// OrderService orderService = new OrderService();
		for (int i = 0; i < 100; i++) {
			new Thread(new OrderService()).start();
		}
	}

}
