package cn.han.spring.spring2;

import cn.han.spring.spring2.impl.SeeIml;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

/**
 * @Author han_s
 * @Date 2022/8/9 9:59
 * @ProName maven_test
 */
public class JDKProxyDemoOne {
    public static void main(String[] args) {
        test1();
//        BigDecimal
    }
    public static void test1(){
        SeeIml seeIml = new SeeIml();
        Proxy.newProxyInstance(seeIml.getClass().getClassLoader(), seeIml.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(proxy.toString());
                Object invoke = method.invoke(proxy, args);
                return invoke;
            }
        });
    }
}
