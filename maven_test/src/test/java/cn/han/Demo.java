package cn.han;

import cn.han.pojo.Student;
import org.junit.Test;

import java.util.*;

public class Demo {
    @Test
    public void testList(){
        Set<Student> hashSet = new HashSet<Student>();
        ArrayList<String> ar = new ArrayList<String>();
        Student s1 = new Student("小米",3000);
        Student s2 = new Student("小米",3000);
        Student s3 = new Student("华为",5000);
        Student s4 = new Student("华为",5000);
        Student s5 = new Student("苹果",6000);
        hashSet.add(s1);
        hashSet.add(s2);
        hashSet.add(s3);
        hashSet.add(s4);
        hashSet.add(s5);

        System.out.println(hashSet.toString());
//        for (String s : ar) {
//            if ("3".equals(s)){
//                ar.remove(s);
//            }
//        }
//        for (int i = 0; i < ar.size(); i++) {
//            if ("3".equals(ar.get(i))){
//                ar.remove(i);
//            }
//        }
        System.out.println(ar.toString());
    }

    @Test
    public void testMap(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("c", 3);
        map.put("c", 3);
        System.out.println(map.toString());
//        Set<Map.Entry<String, Integer>> entries = map.entrySet();
//        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String, Integer> next = iterator.next();
//            System.out.println(next.toString());
//        }
    }
    @Test
    public void testRandom(){
        Random rd = new Random();
        for (int i = 0; i <50 ; i++) {
            System.out.print(rd.nextInt(9)+" ");
        }
    }
    @Test
    public void testThread(){
        ThreadLocal<String> tl = new ThreadLocal<>();

        System.out.println(Thread.currentThread());
    }
}
