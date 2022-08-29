package cn.han.design_pattern.proxy;

/**
 * @Author han_s
 * @Date 2022/8/26 15:09
 * @ProName maven_test
 */
public class MyTarget implements Play,Eat{
    @Override
    public void speack() {
        System.out.println("我要开始说话了");
    }

    @Override
    public void cake() {
        System.out.println("吃巧克力蛋糕");
    }
}
