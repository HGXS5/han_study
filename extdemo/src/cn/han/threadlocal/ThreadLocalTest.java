package cn.han.threadlocal;

public class ThreadLocalTest {
    public static void main(String[] args) {
//        ThreadLocal
        SelfTest selfTest = new SelfTest();
        System.out.println(selfTest);
        selfTest.test();

    }

    public static void demo1() {
        int r = 234;
        int g = 0;
        int x = r & (-1);
        System.out.println(x);
    }

    public void demo2() {
    }

    static class SelfTest {
        public void test() {
            System.out.println(this);
        }
    }

}
