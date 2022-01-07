package cn.han.pojo;



public class TestOnly {
    static TestTwo t = new TestTwo();

    static {
        System.out.println("代码块");
    }
    static void tes(){
        System.out.println("静态方法");
    }
    void tr(){
        System.out.println("非静态方法");
    }
    TestOnly(){
        System.out.println("构造器");
    }

    static class Test1{
        Test1(){
            System.out.println("静态内部类");
        }
    }

}
