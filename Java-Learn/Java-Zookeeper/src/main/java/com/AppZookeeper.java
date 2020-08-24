package com;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

public class AppZookeeper {
    //Zk连接地址
    private static final String CONNECT_STRING="127.0.0.1:2181";
    //Session超时时间为5000
    private static final int SESSION_TIMEOUT=5000;
    //使用Java并发包信号量技术控制ZK连接成功才可向下执行 ，否则会阻塞
    private static final CountDownLatch countDownLatch=new CountDownLatch(1);
    //java 操作ZK
    public static void main(String[] args) throws InterruptedException {
        ZooKeeper zooKeeper = null;
        try {
            //创建一个ZK连接
            zooKeeper = new ZooKeeper(CONNECT_STRING, SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    //判断监听节点是否发生变化 连接成功
                    //获取事件状态
                    Event.KeeperState keeperState=event.getState();
                    //获取事件类型
                    Event.EventType eventType= event.getType();
                    if(Event.KeeperState.SyncConnected==keeperState){
                        if (Event.EventType.None==eventType)
                            //事件监听
                            countDownLatch.countDown();//调用方法减一 当信号量为0时，才会向下执行
                            System.out.println("ZK启动连接.............");
                        if (Event.EventType.NodeCreated==eventType)
                            //事件监听 监听创建节点事件 分布式锁实现
                            System.out.println("zk创建了一个节点");
                    }
                }
            });
            countDownLatch.await();//计数器不为零， 则在此会进行阻塞，直到计数器为0
            String path="/temp";
            zooKeeper.exists(path, true);//如果要监听创建节点事件 ，需要加入监听
            //创建节点  ZooDefs.Ids.OPEN_ACL_UNSAFE 表示无需任何权限即可操作
            //CreateMode.EPHEMERAL 表示创建临时节点
            //CreateMode.EPHEMERAL_SEQUENTIAL 表示创建临时节点如果重名  会_0 _1
            //CreateMode. 表示创建持久节点
            //CreateMode.PERSISTENT_SEQUENTIAL 表示创建持久节点如果重名  会_0 _1
            String node = zooKeeper.create(path, "3song".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            //返回字符串为节点名称
            System.out.println("Zookeeper节点名称为："+node);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zooKeeper!=null){
                //创建临时节点时  close 后 临时节点就会消失
                zooKeeper.close();
            }
        }
    }
}
