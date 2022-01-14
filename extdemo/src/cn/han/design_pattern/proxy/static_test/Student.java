package cn.han.design_pattern.proxy.static_test;

public class Student implements People{
    @Override
    public void talk() {
        System.out.println("我是学生");
    }
}
