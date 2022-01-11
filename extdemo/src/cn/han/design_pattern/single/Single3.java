package cn.han.design_pattern.single;

public class Single3 {
    private static Single3 instance = null;

    private Single3() {
    }

    public static Single3 getInstance() {
        if (instance == null) {
            instance = new Single3();
        }
        return instance;
    }
}
