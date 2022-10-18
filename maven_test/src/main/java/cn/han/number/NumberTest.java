package cn.han.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @Author han_s
 * @Date 2022/6/8 16:12
 * @ProName maven_test
 */
public class NumberTest {
    public static void main(String[] args) {

        Map<Integer,Long> map = new HashMap<>();
        map.put(1, 3L);
//        map.merge(1, 2L,Long::sum);
        map.merge(1, 2L, new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) {
               return Long.sum(aLong, aLong2);
            }
        });
        System.out.println(map.get(1));
    }
    public static void test5(){
        int hash = hash("key");
        int n1 = 16;
        int n2 = 32;
        int i1 = (n1 - 1) & hash;
        int i2 = (n2 - 1) & hash;
        System.out.println(i1);
        System.out.println(i2);
    }
    public static void test4(){
        //        List<Integer> numbers = new ArrayList<>();
//        for (int i = 1; i <10 ; i++) {
//            numbers.add(i);
//        }
//        System.out.println(numbers.size());
//        for (int i = 1; i <= numbers.size(); i++) {
//            System.out.println(i+":"+numbers.get(i-1));
//        }
        int hash = hash("name");
        System.out.println("hash:"+hash);
        int n = 16;
        int i = (n - 1) & hash;
        System.out.println(i);
        int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println(MAXIMUM_CAPACITY);
        int f = 2 << 1;
        System.out.println(f);
        for (int j = 1; j <=64 ; j++) {
            int oldCap = 8;
            hash = j;
            int result = hash & oldCap;
            System.out.println("oldCap:"+oldCap+"-hash:"+hash+"="+result);
        }

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
    public static void test3(){
        final int NONE    = 0x0;
        System.out.println(NONE);
    }
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
