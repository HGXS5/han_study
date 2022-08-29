package cn.han.design_pattern.observer;

/**
 * @Author han_s
 * @Date 2022/8/15 11:42
 * @ProName maven_test
 */
public class TwoObserver extends Observer {
    public TwoObserver() {
        subject.bindSubject(this);
    }

    @Override
    public void update() {
        System.out.println("我是监听者two,我监听的对象状态变化了"+subject.getStatus());
    }
}
