package cn.han.spring.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author han_s
 * @Date 2022/8/29 15:55
 * @ProName maven_test
 */
@ComponentScan("cn.han.spring.aop")
public class HanConfig {
    public static void main(String[] args) {
//        Destruction
//        BeanPostProcessor beanPostProcessor =  new BeanPostProcessor() {
//            @Override
//            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//                return null;
//            }
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                return null;
//            }
//        };
    }
}
