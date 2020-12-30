package com.han.bean;

import com.han.config.AppConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {
    public static void main(String[] args) {
        // TWR (Java 7+)
        try(ConfigurableApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class)) {
            Person person = factory.getBean(Person.class);
            System.out.println(person);
        }
    }
}