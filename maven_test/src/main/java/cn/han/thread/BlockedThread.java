package cn.han.thread;

/**
 * @Author han_s
 * @Date 2022/8/31 14:41
 * @ProName maven_test
 */
public class BlockedThread implements Runnable{
    @Override
    public void run() {
        synchronized (BlockedThread.class){
            while (true){
                System.out.println(Thread.currentThread().getName()+"执行");
                WaitingTime.waitSecond(100);
                System.out.println(Thread.currentThread().getName()+"结束");
            }
        }
    }
}
