package cn.han.thread;

public class ThreadTest {

    private static ThreadLocal<String> localVar = new ThreadLocal<String>();

    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        ThreadTest.localVar.set("M");
        new Thread(new Runnable() {
            public void run() {
//                ThreadTest.localVar.set("local_A");
                print("A");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());

                System.out.println(Thread.currentThread().getName());
            }
        }, "A").start();
//        Thread.sleep(1000);

        new Thread(new Runnable() {
            public void run() {
//                ThreadTest.localVar.set("local_B");
                print("B");
                System.out.println("after remove : " + localVar.get());
                System.out.println(Thread.currentThread().getName());
            }
        }, "B").start();

        System.out.println("此时本地线程变量的值："+localVar.get());
        System.out.println(".................");
        System.out.println(ThreadTest.localVar.get());
        System.out.println(Thread.currentThread().getName());
    }


}
