package com;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//卖房接口
interface Home1{
    void maiHome();
}
class Person1 implements Home1{

    @Override
    public void maiHome() {
        System.out.println("我3SONG买个一个房子");
    }
    void naiHome(){} //增加卖房方法时不会报错
}
class JDKProxy implements InvocationHandler{
    private Object object;
    public JDKProxy(Object o){
        this.object=o;
    }
    @Override
    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable {
        System.out.println("我是中介，我负责您的购房业务");
        Object invoke = paramMethod.invoke(object, paramArrayOfObject);
        System.out.println("购房结束");
        return invoke;
    }
}

public class JDKProxyTest {
    public static void main(String[] args) {
        Person1 cl=new Person1();
        JDKProxy jdkProxy=new JDKProxy(cl);
        //不需要new Home()
        Home1 home1 = (Home1) Proxy.newProxyInstance(cl.getClass().getClassLoader(), cl.getClass().getInterfaces(), jdkProxy);
        home1.maiHome();
    }
}
