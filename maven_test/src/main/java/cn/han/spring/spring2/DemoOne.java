package cn.han.spring.spring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author han_s
 * @Date 2022/8/9 10:00
 * @ProName maven_test
 */
public class DemoOne {
    private static SeeInterface seeInterface;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();
//        SeeInterface anInterface = new SeeInterface() {
//            @Override
//            public void test() {
//                System.out.println("new接口并重写方法");
//            }
//        };
//        anInterface.test();
        List<SeeInterface> seeInterfaces = new ArrayList<>();

        seeInterfaces.add(seeInterface);

        for (SeeInterface anInterface : seeInterfaces) {
            anInterface.test();
        }
    }
}
