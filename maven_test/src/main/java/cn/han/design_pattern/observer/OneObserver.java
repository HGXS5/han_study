package cn.han.design_pattern.observer;

/**
 * @Author han_s
 * @Date 2022/8/15 11:33
 * @ProName maven_test
 */
public class OneObserver extends Observer {
    public OneObserver() {
        subject.bindSubject(this);
    }
    @Override
    public void update() {
        System.out.println("我是One,我监听的对象发生了改变"+subject.getStatus());
    }

}
