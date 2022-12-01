package cn.han.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author han_s
 * @Date 2022/8/26 16:05
 * @ProName maven_test
 */
public class ProxyTwo implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        MyTarget myTarget = new MyTarget();
//        HashSet set = new HashSet();



        Object data = null;
       if (proxy instanceof MyTarget){
           data = method.invoke(proxy, args);
       }
        return data;
    }
}
