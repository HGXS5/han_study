package cn.han.sort;

import cn.han.pojo.Person;

import java.util.*;

/**
 * @Author han_s
 * @Date 2022/3/28 12:10
 * @ProName extdemo
 */
public class SortTest {
    public static void main(String[] args) {
        ArrayList<Person> arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        Set set = hashMap.entrySet();
        List list = new ArrayList();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
        arrayList.add(new Person("xd", 14));
        arrayList.add(new Person("xd", 19));
        arrayList.add(new Person("xd", 10));

        arrayList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.compareTo(o2);
            }
        });
        for (Object o : arrayList) {
            System.out.println(o);
        }
    }
}
