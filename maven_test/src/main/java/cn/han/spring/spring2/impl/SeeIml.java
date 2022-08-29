package cn.han.spring.spring2.impl;

import cn.han.spring.spring2.SeeInterface;

/**
 * @Author han_s
 * @Date 2022/8/9 10:23
 * @ProName maven_test
 */
public class SeeIml implements SeeInterface {
    @Override
    public void test() {
        System.out.println("实现了");
    }
}
