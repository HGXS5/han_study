package cn.han.thread.threadlocal;

/**
 * @Author han_s
 * @Date 2023/1/9 14:23
 * @ProName maven_test
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    public static void main(String[] args) {
        
        for (int i = 0; i < 5; i++) {
            new Thread(new MyRun()).start();
        }
    }

    public static class MyRun implements Runnable {
        @Override
        public void run() {
            Integer left = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + "得到了-->" + left);
            threadLocal.set(left - 1);
            System.out.println(Thread.currentThread().getName() + "还剩下-->" + threadLocal.get());
        }
    }
}
