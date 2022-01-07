package cn.han.pojo;

public class Demo {
    /*
     *1.静态方法和静态内部类需要调用才会被初始化
     *2.构造方法在创建实例的时候才会被初始化
     * */
    public static void main(String[] args) {
        TestOnly.Test1 t1 = new TestOnly.Test1();

    }
}
