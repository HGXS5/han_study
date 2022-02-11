package cn.han.spring.spring1.demo;


import cn.han.spring.spring1.dao.HanDao;
import cn.han.spring.spring1.pojo.Person;
import cn.han.spring.spring1.service.HanService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)//表示当前类是测试类
@ContextConfiguration("classpath:application.xml")//定位配置文件位置
public class Demo {
    @Test
    public void test() {
        Person person = new Person();
        person.setAge(13);
        Integer age = person.getAge();
        System.out.println(age);
        System.out.println(person.toString());
    }

    @Test
    public void demo() {
        ApplicationContext cpx = new ClassPathXmlApplicationContext("application.xml");
        Person person = (Person) cpx.getBean("person");
//        Person person = (Person) cpx.getBean(Person.class);
        person.setAge(25);
        System.out.println(person.getAge());
        System.out.println("测一下");
    }
    @Test
    public void  serviceTest(){
//        HanService hs = new HanService();
//        hs.serviceTest();
    }
}
