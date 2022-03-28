package cn.han.rabbitmq.test;

import cn.han.rabbitmq.RabbitMqAll;
import cn.han.rabbitmq.pojo.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * @Author han_s
 * @Date 2022/3/22 21:11
 * @ProName bootMq
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void test() throws JsonProcessingException {
        Map map = new HashMap<String,Person>();
            map.put("小新", new Person("小新", 23));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMapper = objectMapper.writeValueAsString(map);
        List<RedisClientInfo> clientList = redisTemplate.getClientList();
        if (clientList!=null){
            for (RedisClientInfo clientInfo : clientList) {
                System.out.println(clientInfo);
            }
        }

        HashSet hs = new HashSet();
        hs.add("stringName");
        hs.add("hashName");
        hs.add("listName");
        hs.add("zsetName");
        hs.add("setName");
        /*redisTemplate.boundValueOps("stringName").set("string");
        Object stringName = redisTemplate.boundValueOps("stringName").get();
        System.out.println(stringName);*/
        Long delete = redisTemplate.delete(hs);
        if (delete>0){
            //string类型
            redisTemplate.opsForValue().set("stringName", "string" + System.currentTimeMillis());
//            Object name = redisTemplate.boundValueOps("stringName").get();
            Object name = redisTemplate.opsForValue().get("stringName");
            System.out.println(name);
            //hash类型
            redisTemplate.opsForHash().put("hashName", "hashName", "hash"+System.currentTimeMillis());
            Object name2 = redisTemplate.opsForHash().get("hashName", "hashName");
            System.out.println(name2);
            //set类型
            redisTemplate.opsForSet().add("setName", "set" + System.currentTimeMillis());
            Object setName = redisTemplate.opsForSet().randomMember("setName");
            System.out.println(setName);
            //zset类型
            //list类型
        }


//        redisTemplate.opsForValue().append()
    }
}
