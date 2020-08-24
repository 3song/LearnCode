package com;


//单例模式 保证在jvm中只有一个对象

class NewOneSingleton{

    private NewOneSingleton(){

    }

    static private NewOneSingleton newOneSingleton;
    //懒汉式 当需要的时候才会去创建对象
    //多线程可能有对象创建问题 同时应只有一个线程进行访问 解决线程安全 但是由于锁的资源竞争，效率很低
    static public NewOneSingleton getNewOneSingleton() {

        if (newOneSingleton==null){//第一层上锁
            synchronized(NewOneSingleton.class){
                if (newOneSingleton==null)//第二层上锁
                newOneSingleton = new NewOneSingleton();
            }
        }
        //当对象不为空时，无需考虑线程安全问题
        return newOneSingleton;
    }


    //饿汉式 当Class加载时就初始化对象（因为加入了static关键字）
    static private NewOneSingleton newOneSingleton1=new NewOneSingleton();

    static public NewOneSingleton getNewOneSingleton1() {
        //当对象不为空时，无需考虑线程安全问题
        return newOneSingleton1;
    }


    //枚举方式实现单例：线程安全
    //定义一个静态枚举类
    static enum NewOneSingletonEnum{
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private NewOneSingleton newOneSingletonEnum;
        //私有化枚举的构造函数
        NewOneSingletonEnum(){
            newOneSingletonEnum=new NewOneSingleton();
        }
        public NewOneSingleton getNewOneSingleton(){
            return newOneSingletonEnum;
        }
    }

    //对外暴露一个获取NewOneSingleton对象的静态方法
    public static NewOneSingleton getNewOneSingleton2(){
        System.out.println(NewOneSingletonEnum.INSTANCE.getNewOneSingleton());
        return NewOneSingletonEnum.INSTANCE.getNewOneSingleton();
    }
}
public class Singleton {
    public static void main(String[] args) {
        //由于构造函数私用  不能通过new创建   防止new对象太多浪费资源
        //NewOneSingleton newOneSingleton = new NewOneSingleton();
        // singleton1 如果等于 singleton2 就说明只创建了一个对象 懒汉式写法
//        NewOneSingleton singleton1=NewOneSingleton.getNewOneSingleton();
//        NewOneSingleton singleton2=NewOneSingleton.getNewOneSingleton();
//        System.out.println(singleton1==singleton2);
        //饿汉式写法
//          NewOneSingleton singleton1=NewOneSingleton.getNewOneSingleton1();
//          NewOneSingleton singleton2=NewOneSingleton.getNewOneSingleton1();
//        System.out.println(singleton1);
//        System.out.println(singleton1==singleton2);


        System.out.println("输出对象"+NewOneSingleton.getNewOneSingleton2());//
        System.out.println(NewOneSingleton.getNewOneSingleton2()==NewOneSingleton.getNewOneSingleton2());
        System.out.println(NewOneSingleton.NewOneSingletonEnum.INSTANCE.getNewOneSingleton()==NewOneSingleton.NewOneSingletonEnum.INSTANCE.getNewOneSingleton());
    }
}
