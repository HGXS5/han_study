package cn.han.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        try {
            //获取Student的Class对象
            Class stuClass = Class.forName("cn.han.pojo.Book");
//            stuClass.get
            //说明一下，这里形参不能写成Integer.class，应写为int.class
            //获取注解所标注的方法
            Method stuMethod = stuClass.getMethod("test");
            Field price = stuClass.getDeclaredField("price");
            Field name = stuClass.getDeclaredField("name");
            Field address = stuClass.getField("address");
            System.out.println(address.getName());

            //获取注解所标注的属性
//            Field price = stuClass.getField("price");
//            Field name = stuClass.getField("name");
            if(stuMethod.isAnnotationPresent(TestAnnotation.class)){
                System.out.println("Student类上配置了logAnnotation注解！");
                //获取该元素上指定类型的注解
                TestAnnotation logAnnotation= stuMethod.getAnnotation(TestAnnotation.class);
                //打印
                System.out.println("actionDesc: " +logAnnotation.actiondese() + ",actionvalue: " + logAnnotation.testVales().length);
            }else{
                System.out.println("Student类上没有配置logAnnotation注解！");
            }

            if (price.isAnnotationPresent(TestAnnotation.class)){
                TestAnnotation priceAnnotation = price.getAnnotation(TestAnnotation.class);
                TestAnnotation nameAnnotation = name.getAnnotation(TestAnnotation.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


}

