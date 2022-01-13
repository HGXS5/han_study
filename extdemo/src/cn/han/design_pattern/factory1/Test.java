package cn.han.design_pattern.factory1;

public class Test {
    public static void main(String[] args) {
        FactoryPhone xmf = new HuaWeiFactory();
        xmf.createPhone().call();
        System.out.println();
    }
}
