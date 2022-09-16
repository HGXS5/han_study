package cn.han.thread.threadtest;

/**
 * @Author han_s
 * @Date 2022/8/31 15:16
 * @ProName maven_test
 */
public class ThreadSort01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "执行"),"thread1");
        Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "执行"),"thread2");
        Thread thread3 = new Thread(() -> System.out.println(Thread.currentThread().getName() + "执行"),"thread3");

        thread1.start();
        thread1.join();//实际上让主线程等待子线程执行完成
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}
