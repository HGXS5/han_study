package cn.han.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author han_s
 * @Date 2022/8/31 14:33
 * @ProName maven_test
 *
 * 验证线程的TIMED_WARTING状态
 */
public class WaitingTime implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"执行");
            waitSecond(100);
            System.out.println(Thread.currentThread().getName()+"结束");
        }
    }

    public static void waitSecond(long time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
