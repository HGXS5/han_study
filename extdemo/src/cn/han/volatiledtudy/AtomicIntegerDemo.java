package cn.han.volatiledtudy;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    private static AtomicInteger in = new AtomicInteger(2);
    public static void main(String[] args) {
        in.set(5);
//        getAndIncrementDemo2();
        getIncrementAndGet();
        System.out.println("getAndIncrement:"+in);
        System.out.println(in.get());
    }

    private static void getAndIncrementDemo(){
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    in.getAndIncrement();
                }
            }
        };
    }
    private static void getAndIncrementDemo2() {

        for (int i = 0; i < 10; i++) {
            in.getAndIncrement();
        }
    }
    private static void getIncrementAndGet() {

        for (int i = 0; i < 10; i++) {
            in.incrementAndGet();
        }
    }
}
