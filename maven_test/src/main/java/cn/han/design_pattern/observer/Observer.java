package cn.han.design_pattern.observer;

/**
 * @Author han_s
 * @Date 2022/8/15 11:31
 * @ProName maven_test
 */
public abstract class Observer {
//    protected Subject subject;
    protected static final Subject subject;

    static {
        subject = new Subject();
        System.out.println("可以初始化subject");
    }
    public abstract void update();
}
