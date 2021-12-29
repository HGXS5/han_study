package cn.han.thread;

import org.omg.CORBA.LongHolder;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

public class TreadDemo {

    public static void main(String[] args) {
        test();
    }

    public static void test() {

//        ThreadRunnable tr = new ThreadRunnable();
//        new Thread(tr).start();
//
//        ThreadNormal tn = new ThreadNormal();
//        tn.start();
//
//        ThreadCallable tc = new ThreadCallable();
//        FutureTask<Object> objectFutureTask = new FutureTask<Object>(tc);
//        new Thread(objectFutureTask).start();
//
//        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            ThreadNormal tn = new ThreadNormal();
//            tn.run();//单纯运行方法，没有进行向cpu申请内存
            tn.start();//首先向cpu申请内存，申请成功后才会运行run方法

        }
//        threadPools();
    }

    public static void threadPools() {

        /*
         * 四种线程池的使用
         * 1、newCachedThreadPool
         *       创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
         * 2、newFixedThreadPool
         *       创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
         * 3、newScheduledThreadPool
         *       创建一个定长线程池，支持定时及周期性任务执行。
         * 4、newSingleThreadExecutor
         *       创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
         * */
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        //第一种
        //ThreadPoolExecutor
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        System.out.println(start);
        ThreadNormal threadNormal = new ThreadNormal();
        for (int i = 0; i < 10; i++) {
//            cachedThreadPool.submit(new ThreadNormal());
            cachedThreadPool.execute(new ThreadNormal());
        }


        //第二种
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//
//        fixedThreadPool.submit(new ThreadNormal());
//        fixedThreadPool.submit(new ThreadRunnable());
//        fixedThreadPool.submit(new ThreadCallable());
//        fixedThreadPool.submit(new ThreadNormal());

    }

    static class ThreadNormal extends Thread {
        String lock = "lock";
        Object o = new Object();
        @Override
        public void run() {
            synchronized (this) {
                try {
                    Thread.sleep(1000 * 5);
                    System.out.println("thread...." + Thread.currentThread().getName());
                    System.out.println("run线程开始执行时间点：" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("runnable......." + Thread.currentThread().getName());
            System.out.println("Runnable线程开始执行时间点：" + System.currentTimeMillis());
        }
    }

    static class ThreadCallable implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("call....." + Thread.currentThread().getName());
            System.out.println("Callable线程开始执行时间点：" + System.currentTimeMillis());
            return null;
        }
    }

}
