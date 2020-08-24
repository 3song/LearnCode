/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.lock.api;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月11日 下午6:01:24<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public abstract class ZookeeperAbstractLock implements ExtLock {
	// 集群连接地址
	protected String CONNECTION = "127.0.0.1:2181";
	// zk客户端连接
	protected ZkClient zkClient = new ZkClient(CONNECTION);
	// path路径
	protected String lockPath = "/path";
	protected CountDownLatch countDownLatch = new CountDownLatch(1);

	public void getLock() {
		if (tryLock()) {
			System.out.println("####获取锁成功######");
		} else {
			waitLock();
			getLock();
		}
	}

	// 获取锁
	public abstract boolean tryLock();

	// 等待锁
	public abstract void waitLock();

	public void unLock() {
		if (zkClient != null) {
			System.out.println("#######释放锁#########");
			zkClient.close();
		}
	}

}
