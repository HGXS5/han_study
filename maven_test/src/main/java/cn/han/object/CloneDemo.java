package cn.han.object;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
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
        testClass();
    }
    public static void testClass(){
//        String qualifiedClassName = "com.wasu.cp.node.message.SopplusUgcFdsMessageHandler";
        String qualifiedClassName = "cn.han.object.Animal";
        try {
            Class<?> clz = Class.forName(qualifiedClassName);
            String beanName = StringUtils.uncapitalize(clz.getSimpleName());
            System.out.println(beanName);
        } catch (ClassNotFoundException e) {
            System.out.println("errr");
        }
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

    public static void demo(){
        Dog dog = new Dog();
//        Dog clone = (Dog) dog.clone();
    }



}
