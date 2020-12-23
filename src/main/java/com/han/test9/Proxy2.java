package com.han.test9;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Proxy2 {

    public static void main(String[] args) {
        /**
         * 1. newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
         *                  返回指定接口的代理类的实例，该实例将方法调用分派到指定的调用处理程序
         *          参数：
         *          loader 定义代理类的类加载器
         *          interfaces 代理类要实现的接口列表
         *          h 调用处理程序，将方法调用调度到
         *          返回：
         *          具有由指定类加载器定义并实现指定接口的代理类的指定调用处理程序的代理实例。
         */
        List<String> list = new ArrayList<String>();
        Class<?> clazz = list.getClass();
        ListProxy<String> myProxy = new ListProxy<String>(list);
        List<String> newList = (List<String>) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                myProxy);
        newList.add("apple");
        newList.add("banana");
        newList.add("orange");
    }
}
