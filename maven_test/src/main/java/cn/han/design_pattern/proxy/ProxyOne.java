package cn.han.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author han_s
 * @Date 2022/8/26 14:40
 * @ProName maven_test
 */
public class ProxyOne implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyTarget myTarget = new MyTarget();
        System.out.println("我不是匿名内部类");
        Object data = method.invoke(myTarget, args);
        return data;
    }
}
