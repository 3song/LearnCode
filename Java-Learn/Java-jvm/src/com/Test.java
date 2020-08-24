package com;

import java.text.DecimalFormat;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        byte[] bytes01=new byte[1*1024*1024];
        System.out.println("分配了1M空间");
        jvmInfo();
        Thread.sleep(3000);
        byte[] bytes02=new byte[4*1024*1024];
        System.out.println("分配了4M空间");
        jvmInfo();
    }

    static private String toM(long maxMemory){
        float num = (float) maxMemory / (1024 * 1024);
        DecimalFormat decimalFormat= new DecimalFormat("0.00");
        String s=decimalFormat.format(num);
        return s;
    }
    public static void jvmInfo(){
        //最大内存配置信息
        long maxMemory=Runtime.getRuntime().maxMemory();
        System.out.println("最大内存为:"+maxMemory+","+toM(maxMemory)+"M");
        //当前空闲内存
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("当前空闲内存为:"+freeMemory+","+toM(freeMemory)+"M");
        //已使用内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("已使用内存:"+totalMemory+","+toM(totalMemory)+"M");
    }
}
