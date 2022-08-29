package cn.han.interfaceAndImpl;

import cn.han.interfaceAndImpl.impl.IntefaceImplOne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author han_s
 * @Date 2022/7/28 9:00
 * @ProName maven_test
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringCounfigrtion.class)
public class SpringTestDemo {
    @Autowired
    public IntefaceImplOne one;
    @Test
    public void test1(){
        one.speak();
    }

}
