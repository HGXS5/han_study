package cn.han.interfaceAndImpl;

/**
 * @Author han_s
 * @Date 2022/9/5 11:16
 * @ProName maven_test
 */
public abstract class HanAbstarct implements HanInterface{
    public HanAbstarct() {
        test();
    }

    @Override
    public void hanDemo1() {
        System.out.println("我在HanAbstract执行了");
    }

    protected abstract void test();

    public void testHan(){
        hanDemo1();
    }
}
