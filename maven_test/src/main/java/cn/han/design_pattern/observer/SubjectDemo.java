package cn.han.design_pattern.observer;

/**
 * @Author han_s
 * @Date 2022/8/15 11:45
 * @ProName maven_test
 */
public class SubjectDemo {
    public static void main(String[] args) {
//        Subject subject = new Subject();
//        new OneObserver();
//        subject.setStatus(2);
//        new TwoObserver();
//        subject.setStatus(3);
        new OneObserver();
        new TwoObserver();
        Observer.subject.setStatus(3);


    }
}
