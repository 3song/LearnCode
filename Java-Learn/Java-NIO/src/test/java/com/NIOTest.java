package com;

import org.junit.jupiter.api.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class NIOTest {
    /*
     * @Author 陈磊
     * @Description //TODO  NIO 缓冲区 主要存放数据
     * 类型
     * ByteBuffer
     * LongBuffer
     * IntegerBuffer
     * FloatBuffer
     * DoubleBuffer
     * 没有Boolean 类型
     * @Date
     * @Param
     * 主要参数
     * private int mark = -1;
     * private int position = 0; 缓冲区开始位置 默认从零开始
     * private int limit; // 界面表示缓冲区可用大小
     * private int capacity; //缓冲区最大容量 ，一旦声明，则不能修改
     * 核心方法
     * put() 存放数据
     * get() 获取数据
     * @return
     **/
    @Test
    public void test(){
        try {
            //初始化buffer大小
            //这就是工厂模式创建对象
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            System.out.println("缓冲区开始位置："+byteBuffer.position());
            System.out.println("缓冲区可用大小："+byteBuffer.limit());
            System.out.println("缓冲区最大容量："+byteBuffer.capacity());
            System.out.println("--------------------------");
            System.out.println("向ByteBuffer存放数据");
            byteBuffer.put("12345".getBytes());
            System.out.println("缓冲区开始位置："+byteBuffer.position());
            System.out.println("缓冲区可用大小："+byteBuffer.limit());
            System.out.println("缓冲区最大容量："+byteBuffer.capacity());
            System.out.println("--------------------------");
            System.out.println("读取数据");
            //开启读取模式 将position 设为零 这时读取会从0到数据的长度， 不开启读取就会越界
            byteBuffer.flip();//从零开始
            System.out.println("开启读取模式缓冲区开始位置："+byteBuffer.position());
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes); //获取所有缓冲区的值
            System.out.println(new String(bytes,0,bytes.length));
            System.out.println("缓冲区开始位置："+byteBuffer.position());
            System.out.println("缓冲区可用大小："+byteBuffer.limit());
            System.out.println("缓冲区最大容量："+byteBuffer.capacity());
            System.out.println("-------------重复读取数据------------");
            byteBuffer.rewind();//从上一次读取的地方读取
            System.out.println("开启读取模式缓冲区开始位置："+byteBuffer.position());
            byteBuffer.get(bytes); //获取所有缓冲区的值
            System.out.println(new String(bytes,0,bytes.length));
            System.out.println("缓冲区开始位置："+byteBuffer.position());
            System.out.println("缓冲区可用大小："+byteBuffer.limit());
            System.out.println("缓冲区最大容量："+byteBuffer.capacity());
            //清空缓冲区  只是数据被遗忘（只是下标归0了）   还是可以获取到
            byteBuffer.clear();
            System.out.println("-------------清空缓冲区------------");
            System.out.println("缓冲区开始位置："+byteBuffer.position());
            System.out.println("缓冲区可用大小："+byteBuffer.limit());
            System.out.println("缓冲区最大容量："+byteBuffer.capacity());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
