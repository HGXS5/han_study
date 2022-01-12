package cn.han.design_pattern.single;

/**
 * 懒汉式
 * 缺点：线程不安全
 * 优点：解决了饿汉式过早消耗内存
 */
public class Single1 {
    private static Single1 instance = null;
    private Single1() {
    }
    public static Single1 getInstance() {
        if (instance == null) {

            instance = new Single1();
        }
        return instance;
    }
}
