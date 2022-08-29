package cn.han.number;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author han_s
 * @Date 2022/6/8 16:12
 * @ProName maven_test
 */
public class NumberTest {
    public static void main(String[] args) {
//        List<Integer> numbers = new ArrayList<>();
//        for (int i = 1; i <10 ; i++) {
//            numbers.add(i);
//        }
//        System.out.println(numbers.size());
//        for (int i = 1; i <= numbers.size(); i++) {
//            System.out.println(i+":"+numbers.get(i-1));
//        }
        final int NONE    = 0x0;
        System.out.println(NONE);
    }
    public static void test1(){
        //        int d = 100000 >>> 16;
        int d = -2 << 3;
        System.out.println(d);
    }
    public static void test2(){
        int i = -1/ 10000;
        System.out.println(i);
    }
}
