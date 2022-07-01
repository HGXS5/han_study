package cn.han.javaroot;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * @Author han_s
 * @Date 2022/6/8 15:03
 * @ProName maven_test
 */
public class ReflectionDemo {
    public static void main(String[] args) {

    test();
    }
    @CallerSensitive
    public static void test(){
        Class<?> callerClass = Reflection.getCallerClass();
        String name = callerClass.getName();
        System.out.println(name);
    }
}
