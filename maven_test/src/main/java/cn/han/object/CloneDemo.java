package cn.han.object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author han_s
 * @Date 2022/7/1 9:06
 * @ProName maven_test
 */
public class CloneDemo {
    public static void main(String[] args) {

    }

    private void sortComparableTest() {
        List<Fish> fishs = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            fishs.add(new Fish("草鱼", "游泳", i));
        }
        System.out.println(fishs.toString());
        Collections.sort(fishs);
        System.out.println("排序后：" + fishs.toString());
    }

    private void cloneTestOne() throws CloneNotSupportedException {
        Animal a = new Animal();
        a.setFish(new Fish("鲤鱼", "跳龙门", 1));
        Animal clone = (Animal) a.clone();
        if (a.equals(clone)) {
            System.out.println("animal克隆对象相同");
        }
        Fish fish = a.getFish();
        System.out.println(fish.toString());
        Fish cloneFish = clone.getFish();
        cloneFish.setName("猫");
        if (fish.equals(cloneFish)) {
            System.out.println("fish克隆对象相同");
        }
        System.out.println(fish.toString());
    }
}
