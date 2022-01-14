package cn.han.design_pattern.proxy.static_test;

public class Teacher implements People{
    @Override
    public void talk() {
        System.out.println("我是老师");
    }
}
