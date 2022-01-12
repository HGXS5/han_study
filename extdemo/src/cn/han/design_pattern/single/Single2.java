package cn.han.design_pattern.single;

import java.io.File;
import java.io.IOException;

/**
 * 饿汉式
 * 优点：线程安全
 * 缺点：过早消耗内存
 *
 */
public class Single2 {
    private static Single2 single2 = new Single2();
    private Single2(){}

    public static Single2 getInstance(){
        return single2;
    }

}
