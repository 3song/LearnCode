/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.lock.api.impl;

import com.lock.api.ZookeeperAbstractLock;
import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月11日 下午6:07:39<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class ZookeeperDistributeLock extends ZookeeperAbstractLock {

	@Override
	public boolean tryLock() {
		try {
			//通过创建临时节点"/path"方式 获取锁
			zkClient.createEphemeral(lockPath);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void waitLock() {
		//未获取到锁的线程 开始监听已经获取到锁的线程的节点删除事件
		// 使用zk临时事件监听
		IZkDataListener iZkDataListener = new IZkDataListener() {
			// 当节点删除，也就是锁资源已经被上一个线程所释放  最终也只会有一个节点能执行countDown()
			public void handleDataDeleted(String path) throws Exception {
				if (countDownLatch != null) {
					countDownLatch.countDown();
				}
			}

			public void handleDataChange(String arg0, Object arg1) throws Exception {
				//节点发生改变时
			}
		};
		// 注册事件通知
		zkClient.subscribeDataChanges(lockPath, iZkDataListener);
		if (zkClient.exists(lockPath)) {
			//如果zk中存在lockPath节点 ，就表示其他线程都没有获取锁
			countDownLatch = new CountDownLatch(1);
			try {
				countDownLatch.await();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// 监听完毕后，移除事件通知
		zkClient.unsubscribeDataChanges(lockPath, iZkDataListener);
	}

}
