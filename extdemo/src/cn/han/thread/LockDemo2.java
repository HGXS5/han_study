package cn.han.thread;

/**
 * 1.等待
 *  每个线程执行同步方法或者同步代码块过程中，将该线程设置为等待状态wait()方法
 * 2.唤醒
 *  另起一个线程，专门用来唤醒同一种锁资源的线程
 *
 */
public class LockDemo2 {
    //同步资源
    private synchronized void writer() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+":获得了写操作");
        wait();
        System.out.println(Thread.currentThread().getName()+":被唤醒");
        Thread.sleep(1000*6);
        System.out.println(Thread.currentThread().getName()+":执行完毕");
    }
    //唤醒
    private synchronized void woken(){
        notifyAll();
    }
    //执行资源线程
    private  Runnable writerRun(){
        Runnable writer = new Runnable() {
            @Override
            public void run() {
                try {
                    writer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return writer;
    }
    //执行唤醒线程
    private Runnable wokenRun(){
        Runnable woken = new Runnable() {
            @Override
            public void run() {
                woken();
            }
        };
        return woken;
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main：" + Thread.currentThread().getName());
        LockDemo2 ld = new LockDemo2();
        //获取资源runable
        Runnable writerRun = ld.writerRun();
        //获取唤醒runable
        Runnable wokenRun = ld.wokenRun();
        //执行资源线程
        Thread t1 = new Thread(writerRun, "t1");
        Thread t2 = new Thread(writerRun, "t2");
        Thread t3 = new Thread(writerRun, "t3");
        //唤醒资源线程
        Thread thread = new Thread(wokenRun, "woken");

        //执行线程
        t1.start();
        t2.start();
        t3.start();
        thread.start();

    }

}
