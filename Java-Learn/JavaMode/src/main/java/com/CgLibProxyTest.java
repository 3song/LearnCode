package com;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//卖房接口
interface Home2{
    void maiHome();
}
class Person2 implements Home2{

    @Override
    public void maiHome() {
        System.out.println("我3SONG买个一个房子");
    }
}
//Cglib 动态代理
class CgLibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] arrays, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是中介，我负责您的购房业务");
        Object o = methodProxy.invokeSuper(obj, arrays);
        System.out.println("购房结束");
        return null;
    }
}
public class CgLibProxyTest {
    public static void main(String[] args) {
        CgLibProxy cgLibProxy = new CgLibProxy();
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Person2.class);
        enhancer.setCallback(cgLibProxy);
        //不需要new Home()
        Home2 home2= (Home2) enhancer.create();
        home2.maiHome();
    }
}
