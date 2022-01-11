package cn.han.design_pattern.single;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SingleTest {
    public static void main(String[] args) {
        try {
            for (int i = 0; i <5 ; i++) {
                new Thread(new ThreadRunnable()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class ThreadCallable implements Callable {
        @Override
        public Object call() throws Exception {
            Single3 instance = Single3.getInstance();
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
