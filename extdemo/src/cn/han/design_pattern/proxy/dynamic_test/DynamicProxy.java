package cn.han.design_pattern.proxy.dynamic_test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理 jdk：子类实现父类接口
 */
public class DynamicProxy implements InvocationHandler{
    public static void main(String[] args) {
        Cat c = new Cat();


        Object proxyInstance = Proxy.newProxyInstance(Cat.class.getClassLoader(),Cat.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                method.invoke(new Cat(), args);
                return null;
            }
        });
        Animal instance = (Animal) proxyInstance;
        instance.call();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
