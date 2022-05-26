package cn.han.rabbitmq;

import cn.han.moreservice.PartOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Map;

@SpringBootApplication
public class RabbitMqAll {
    @Autowired
    @Lazy
   static Map<String, Object> map;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqAll.class);
    }

}
