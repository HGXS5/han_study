package cn.han.aop;

import cn.han.interfaceAndImpl.SpringCounfigrtion;
import cn.han.spring.aop.HanAspect;
import cn.han.spring.aop.HanConfig;
import cn.han.spring.aop.HanPointcuts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author han_s
 * @Date 2022/8/29 15:55
 * @ProName maven_test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HanConfig.class)
//@ContextConfiguration({"classpath:application-aop.xml"})
public class AopDemo {
    @Autowired
    HanPointcuts hanPointcuts;

    @Test
    public void demoOne(){
//        hanPointcuts.deleteOnly();
        hanPointcuts.updateOnly();

        hanPointcuts.deleteOnly();
    }
}
