package cn.han.design_pattern.single;

public class Single3 {
    private static Single3 instance = null;

    private Single3() {
    }

    public static Single3 getInstance() {
        if (instance == null) {//目的就是减少不必要的同步操作
            synchronized (Single3.class) {//解决多线程获取不同的实例问题
                if (instance == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Single3();
                }
            }
        }
        return instance;
    }
}
