package cn.han.thread;

public class TreadDemo {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        TreadRun tr = new TreadRun();
//        tr.start();
        Thread t = new Thread(tr);
        t.start();
        System.out.println(Thread.currentThread().toString());
        System.out.println(Thread.currentThread().getPriority());
        System.out.println(Thread.currentThread().getState());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.currentThread().getThreadGroup());
        System.out.println(Thread.currentThread().getName());
    }

    public static void threadPools(){

    }

    static class TreadRun extends Thread implements Runnable{

        @Override
        public void run() {
            System.out.println("run.......");
        }
    }
}
