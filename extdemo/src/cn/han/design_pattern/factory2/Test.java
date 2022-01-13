package cn.han.design_pattern.factory2;

public class Test {
    public static void main(String[] args) {
        PhoneFactory.creatHWPhone().call();
        PhoneFactory.creatXMPhone().call();
    }
}
