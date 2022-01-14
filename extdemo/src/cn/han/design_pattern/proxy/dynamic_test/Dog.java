package cn.han.design_pattern.proxy.dynamic_test;

public class Dog implements Animal{
    @Override
    public void name() {
        System.out.println("德牧");
    }

    @Override
    public void call() {
        System.out.println("汪汪汪");
    }
}
