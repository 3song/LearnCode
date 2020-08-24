package com.gc;

public class TestStackOut {
    public static int count;
    public static void main(String[] args) {
        inCount();
    }
    public static void inCount(){
        try {
            count++;
            inCount();
        } catch (Throwable e) {
            System.out.println("最大深度为："+count);
            e.printStackTrace();
        }
    }
}
