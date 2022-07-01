package cn.han.interfaceAndImpl.impl;

import cn.han.interfaceAndImpl.IntereFaceDemo1;
import org.springframework.stereotype.Service;

/**
 * @Author han_s
 * @Date 2022/6/30 11:03
 * @ProName maven_test
 */
public class InterfaceImplTwo implements IntereFaceDemo1 {
    @Override
    public void speak() {
        System.out.println("two...");
    }
}
