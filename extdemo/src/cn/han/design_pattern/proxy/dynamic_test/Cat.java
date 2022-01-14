package cn.han.design_pattern.proxy.dynamic_test;

public class Cat implements Animal{
    @Override
    public void name() {
        System.out.println("珍珠猫");
    }

    @Override
    public void call() {
        System.out.println("喵喵喵");
    }
}
