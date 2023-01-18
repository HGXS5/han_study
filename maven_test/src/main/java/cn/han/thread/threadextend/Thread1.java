package cn.han.thread.threadextend;

/**
 * @Author han_s
 * @Date 2022/12/15 14:45
 * @ProName maven_test
 */
public class Thread1 extends ThreadExented{
    Thread1() {
        super.start();
    }
    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
    }
}
