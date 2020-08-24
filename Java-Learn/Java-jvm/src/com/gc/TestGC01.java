package com.gc;

import java.util.ArrayList;
import java.util.List;

public class TestGC01 {
    public static void main(String[] args) {
        TestGC01 testGC01=new TestGC01();
        testGC01=null;//表示当前对象不可达
        //System.gc();//手动回收垃圾
        List<Object> objectList=new ArrayList<>();
        for (int i = 0; i <40 ; i++) {
            System.out.println("i:"+i);
            Byte[] bytes=new Byte[1*1024*1024];
            objectList.add(i);
        }
        System.out.println("添加成功");
    }


    @Override
    protected void finalize() throws Throwable {
        //GC回收垃圾前调用的方法，并不会100%执行
        System.out.println("垃圾回收调用。。。。。。。。。");
    }
}
