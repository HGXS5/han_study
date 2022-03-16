package cn.han.thread;

public class ThreadAll {
    public static void main(String[] args) {
        System.out.println("main:"+Thread.currentThread().getName());
        ThreadAll ta = new ThreadAll();
        ta.funOne();
        ta.funTwo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadOne:"+Thread.currentThread().getName());
            }
        },"threadOne").start();
    }

    public static void funOne(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadFunOne:"+Thread.currentThread().getName());
            }
        },"threadFunOne").start();
        System.out.println("funOne:"+Thread.currentThread().getName());
    }
    public void funTwo(){
        System.out.println("funTwo:"+Thread.currentThread().getName());
    }
}
