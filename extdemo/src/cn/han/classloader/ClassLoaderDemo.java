package cn.han.classloader;

import cn.han.thread.TreadDemo;

import java.net.URL;
import java.sql.DriverManager;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = TreadDemo.class.getClassLoader();
        URL resource = classLoader.getResource("cn/han/nio/han.txt");
        String substring = resource.toString().substring(resource.toString().indexOf("/") + 1);
        System.out.println(substring);
        ClassLoader parent = classLoader.getParent();
        System.out.println(classLoader);
        System.out.println(parent);
        /*ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        TreadDemo td = new TreadDemo();*/

    }
}
