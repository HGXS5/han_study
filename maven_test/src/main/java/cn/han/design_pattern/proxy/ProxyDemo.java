package cn.han.design_pattern.proxy;

import com.rabbitmq.tools.json.JSONUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author han_s
 * @Date 2022/8/26 15:17
 * @ProName maven_test
 */
public class ProxyDemo {
    public static void main(String[] args) {
        normalTwo();
    }

    public static void anonymousClassOne(){
        MyTarget target = new MyTarget();
//        SecurityManager sm = System.getSecurityManager();
//        System.out.println(sm.toString());
        Play play = (Play) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[]{Play.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("begin");
                Object data = method.invoke(target, args);
                System.out.println("end");
                return data;
            }
        });
        play.speack();
    }
    public static void normalOne(){
        Play play = (Play) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[]{Play.class}, new ProxyOne());
        play.speack();
    }
    public static void anonymousClassTwo(){
        MyTarget target = new MyTarget();
//        SecurityManager sm = System.getSecurityManager();
//        System.out.println(sm.toString());
        Eat eat = (Eat) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[]{Play.class,Eat.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("begin");
                Object data = method.invoke(target, args);
                System.out.println("end");
                return data;
            }
        });
        eat.cake();

    }
    public static void normalTwo(){
        Eat eat = (Eat) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[]{Play.class, Eat.class}, new ProxyTwo());
        eat.cake();
    }
}
