package cn.han.design_pattern.single;

public class SingleTest1 {
    private static SingleTest1 signleton = null;
    private SingleTest1(){};

    public static SingleTest1 getInstance() {
        if (signleton == null) {
            /*try {
                //创建对象睡2秒
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            signleton = new SingleTest1();
        }
        return signleton;
    }



    public static void main(String[] args) {
        //三个线程测试单例
        new Thread(new Runnable() {public void run() { System.out.println(SingleTest1.getInstance().hashCode()); }}).start();
        new Thread(new Runnable() {public void run() { System.out.println(SingleTest1.getInstance().hashCode()); }}).start();
        new Thread(new Runnable() {public void run() { System.out.println(SingleTest1.getInstance().hashCode()); }}).start();

    }

}
