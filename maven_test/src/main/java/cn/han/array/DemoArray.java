package cn.han.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author han_s
 * @Date 2022/5/31 14:45
 * @ProName maven_test
 */
public class DemoArray {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        System.out.println("list1添加完成：" + list1.toString());
        list1.subList(0, 8).clear();
        System.out.println("list1过滤之后："+list1.toString());
//        List<Integer> list2 = new ArrayList<>();
//        for (int i = 13; i < 50; i++) {
//            list2.add(i);
//        }
//        System.out.println("list2添加完成：" + list2.toString());
//        demo(list1, list2);
    }

    public static void test(List<Integer> list1, List<Integer> list2) {
        for (Integer integer : list2) {
            for (int i = 0; i < list1.size(); i++) {
                if (integer.equals(list1.get(i))) {
                    list1.remove(i);
                    break;
                }
            }
        }
        System.out.println(list1.toString());
    }

    public static void demo(List<Integer> list1, List<Integer> list2) {

        for (Integer integer : list2) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).equals(integer)) {
                    System.out.println("break时两者得值：" + "list1=" + list1.get(i) + "---" + "list2=" + integer);
                    list1.remove(list1.get(i));
                    break;
//                } else if (list2.get(list2.size() - 1).equals(integer)) {
//                    System.out.println("remove：" + list1.get(i));
//                    list1.remove(list1.get(i));
                }
            }
        }
        System.out.println(list1.toString());
    }
}
