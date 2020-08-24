package com;
//卖房接口
interface Home{
    void maiHome();
    //void naiHome(); 静态代理增加卖房方法所有 对象都要重写次方法
}
class Person implements Home{

    @Override
    public void maiHome() {
        System.out.println("我3SONG买个一个房子");
    }
}
//代理类   中介
class Proxy implements Home{
    //代理对象
    private Person person;
    public Proxy(Person person) {
        this.person=person;
    }
    @Override
    public void maiHome() {
        System.out.println("我是中介，我负责您的购房业务");
        person.maiHome();
        System.out.println("购房结束");
    }
}

public class HomeTest {
    public static void main(String[] args) {
        //使用代理类 来控制买房的流程
        Home home = new Proxy(new Person());
        home.maiHome();
    }
}
