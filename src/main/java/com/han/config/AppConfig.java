package com.han.config;

import com.han.bean.Car;
import com.han.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Car car() {
        return new Car("丰田",230);
    }

    @Bean
    public Person person() {
        return new Person("波波",24);
    }
}
