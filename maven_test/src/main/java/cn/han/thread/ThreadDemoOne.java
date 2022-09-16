package cn.han.thread;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author han_s
 * @Date 2022/7/27 17:09
 * @ProName maven_test
 */
public class ThreadDemoOne {
    public static void main(String[] args) {
//        test1();
        ThreadLocal<DateFormat> formatThreadLocal = new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd");
            }
        };
    }

    public static void test1() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(1);
        System.out.println(atomicInteger.get());
        Thread thread = new Thread();
    }
    public static void test2(){
        AtomicInteger count = new AtomicInteger();
    }
}
