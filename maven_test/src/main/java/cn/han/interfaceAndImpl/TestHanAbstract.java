package cn.han.interfaceAndImpl;

/**
 * @Author han_s
 * @Date 2022/9/13 13:55
 * @ProName maven_test
 */
public class TestHanAbstract extends HanAbstarct{
    public TestHanAbstract() {
        super();
    }

    @Override
    public void hanDemo1() {
        System.out.println("我是HanAbstract的子类，重写了hanDemo1方法");
    }

    @Override
    protected void test() {
        System.out.println("实现HanAbstract方法test");
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        HanAbstarct han = new TestHanAbstract();
        han.hanDemo1();
    }
}
