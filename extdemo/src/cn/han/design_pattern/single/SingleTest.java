package cn.han.design_pattern.single;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SingleTest {
    public static void main(String[] args) {
        try {
//            for (int i = 0; i <3 ; i++) {
//                ThreadCallable threadCallable = new ThreadCallable();
//                FutureTask<Single3> Single3FutureTask = new FutureTask<Single3>(threadCallable);
//                System.out.println("Single3FutureTask:"+Single3FutureTask.hashCode());
//                new Thread(Single3FutureTask).start();
//                System.out.println("instance:"+Single3FutureTask.get().hashCode());
//            }
            ThreadCallable threadCallable = new ThreadCallable();
            FutureTask<Single3> Single3FutureTask = new FutureTask<Single3>(threadCallable);
            System.out.println("Single3FutureTask:"+Single3FutureTask.hashCode());
            new Thread(Single3FutureTask).start();

            ThreadCallable threadCallable1 = new ThreadCallable();
            FutureTask<Single3> Single3FutureTask1 = new FutureTask<Single3>(threadCallable1);
            System.out.println("Single3FutureTask:"+Single3FutureTask1.hashCode());
            new Thread(Single3FutureTask1).start();
            /*
            * 倘若在每个线程执行完就获取实例的话，就会阻止下一个线程执行。
            *   直到当前线程执行完后，获取到实例，才会让下一个线程继续执行。
            *   此时，当前线程已经完成初始化实例，下一个线程还未执行
            *   当下一线程继续执行时，该实例已经完成初始化，所以获取到的实例就是当前线程初始化过的实例
            *
            * 所以，要想使用callable接口进行多线程验证单例，首先应该注意现将多个线程执行之后，然后再分别获取实例。
            * 这样才能懒汉式单例线程是不安全的。
            *
            * 使用其他两种类型线程进行获取实例的时候，并不会出现这种问题
            * */
            System.out.println("instance:"+Single3FutureTask.get().hashCode());
            System.out.println("instance:"+Single3FutureTask1.get().hashCode());
//            for (int i = 0; i <5 ; i++) {
//                ThreadRunnable runnable = new ThreadRunnable();
//                new Thread(runnable).start();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class ThreadCallable implements Callable {
        @Override
        public Object call() throws Exception {
            Single3 instance = Single3.getInstance();
            System.out.println(instance.hashCode());
            return instance;
        }
    }
    static class ThreadRunnable implements Runnable{
        @Override
        public void run() {
            Single3 instance = Single3.getInstance();
            System.out.println(instance.hashCode());
        }
    }
}
