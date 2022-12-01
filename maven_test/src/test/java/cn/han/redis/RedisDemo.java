package cn.han.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBuckets;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author han_s
 * @Date 2022/10/25 16:42
 * @ProName maven_test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class})
//@ContextConfiguration(classes = {RedisConfig.class,RedissonConfig.class,RedisService.class,DefaultThreadPoolConfig.class})
public class RedisDemo {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    RedisService redisService;

    @Test
    public void test1() {
        SetOperations<String, Object> opsForSet = redisTemplate.opsForSet();//set
        opsForSet.add("testSet", "123", "456");
//        System.out.println("set:"+opsForSet.pop("testSet"));
        Set<Object> testSet = opsForSet.members("testSet");
        System.out.println(testSet.toString());
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();//String
        opsForValue.append("testString", "hanString");
        System.out.println("string:" + opsForValue.get("testString"));
        ZSetOperations<String, Object> opsForZSet = redisTemplate.opsForZSet();//sorted set
        opsForZSet.add("testZset", "123", 1);
        opsForZSet.add("testZset", "456", 5);
        opsForZSet.add("testZset", "789", 2);
        RedisOperations<String, Object> operations = opsForZSet.getOperations();
        Set<Object> testZset = operations.boundZSetOps("testZset").range(0, 5);
        System.out.println(testZset.toString());

        ListOperations<String, Object> opsForList = redisTemplate.opsForList();//list
        opsForList.leftPush("listOne", "123");
        opsForList.leftPush("listOne", "456");
        opsForList.leftPush("listOne", "789");
        List<Object> listOne = opsForList.range("listOne", 0, 3);
        int size = listOne.size();
        System.out.println(listOne.toString() + size);

        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
//        opsForHash.put("hashTest","name","小三");
//        opsForHash.put("hashTest","age","20");
        String age = (String) opsForHash.get("hashTest", "age");
        Map<Object, Object> entries = opsForHash.entries("hashTest");
        System.out.println(age + " " + entries.toString());
    }

    @Test
    public void test2() {
        /*获取客户端*/
        Config config = redissonClient.getConfig();
        System.out.println(config.toString());
        /*不断获取锁*/
        redisService.testLock();
    }
}
