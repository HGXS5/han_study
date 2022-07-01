package cn.han.frg;

import cn.han.interfaceAndImpl.IntereFaceDemo1;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author han_s
 * @Date 2022/6/30 11:03
 * @ProName maven_test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class Demo implements BeanFactoryAware {
    @Autowired
    @Qualifier(value = "intefaceImplOne")
    private IntereFaceDemo1 demo1;

    private DefaultListableBeanFactory beanFactory;

    @Test
    public void test1() {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("cn.han.interfaceAndImpl.impl.InterfaceImplTwo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(beanFactory);
        if (aClass != null) {
            System.out.println(aClass.getSimpleName());
            System.out.println(aClass.getName());
            String beanName = StringUtils.uncapitalize(aClass.getSimpleName());
            System.out.println(beanName);
            if (!beanFactory.containsBean(beanName)) {
                //注册
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(aClass);
                AbstractBeanDefinition beanDefinition = builder.getRawBeanDefinition();
                beanFactory.registerBeanDefinition(beanName, beanDefinition);
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {
                    method.setAccessible(true);
                    try {
                        method.invoke(beanFactory.getBean(beanName));
                        System.out.println(method.getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        demo1.speak();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

}
