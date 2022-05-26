package cn.han.moreservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Author han_s
 * @Date 2022/5/16 11:00
 * @ProName bootMq
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {PartDemo.class,PartOne.class,PartTwo.class})
public class ServicesDemo {
    @Autowired
   private Map<String, Object> map;

    @Test
    public void demo1(){
        Object one = map.get("one");
        if (one instanceof PartOne){
            ((PartOne) one).play();
        }

    }

}
