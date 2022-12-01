package cn.han.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author han_s
 * @Date 2022/10/27 14:38
 * @ProName maven_test
 */
@Service
public class RedisService {
    @Autowired
    RedissonClient redissonClient;

    public void testLock() {
        while (true) {
            try {
                takeLock();
                Thread.sleep(5000);
                System.out.println("当前线程：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void takeLock() {
        System.out.println(Thread.currentThread().getName() + "---进入takeLock方法");
        RLock lock = redissonClient.getLock("keyHanTest");
        if (lock.isLocked()) {
            return;
        }
        try {
            lock.lock(60, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + "---获取锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "---释放锁");
        }


    }
}
