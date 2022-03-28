package cn.han.rabbitmq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author han_s
 * @Date 2022/3/22 21:03
 * @ProName bootMq
 */
public class RedisConfig {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Bean
    public RedisTemplate redisTemplate(){
        return null;
    }
}
