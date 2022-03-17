package cn.han.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private static ReentrantLock rtl = new ReentrantLock();

    public volatile static boolean runFlag;
    //

    //synchronized
    public synchronized void play() throws InterruptedException {
        System.out.println("play：" + Thread.currentThread().getName());
        System.out.println("玩足球");
        runFlag = false;
        while (runFlag != true) {
            System.out.println("等待：" + Thread.currentThread().getName());
            wait();
            System.out.println("被唤醒："+Thread.currentThread().getName());
        }
        speck();
        System.out.println(Thread.currentThread().getName()+":执行完成。。");
    }

    public static synchronized void speck() {
        System.out.println("speck：" + Thread.currentThread().getName());
        System.out.println("诵读");
    }

    private synchronized void run1() {
        notifyAll();
//        notify();
        runFlag = true;
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo ad = new LockDemo();
        System.out.println("main：" + Thread.currentThread().getName());


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ad.play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "demo1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ad.play();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "demo3").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                ad.run1();
            }
        }).start();

    }
}
