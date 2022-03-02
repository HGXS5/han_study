package cn.han.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.han.rabbitmq.config"})
public class RabbitMqAll {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqAll.class);
    }
}
