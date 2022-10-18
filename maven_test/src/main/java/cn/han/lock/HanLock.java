package cn.han.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author han_s
 * @Date 2022/9/16 15:11
 * @ProName maven_test
 */
public class HanLock {
    private Lock thisLock = new ReentrantLock();
    private Lock targetLock = new ReentrantLock();
    //账户的余额
    private Integer balance;
    public void transfer(HanLock target,Integer transferMoney){
        boolean isThisLock = thisLock.tryLock();
        try {
            if (isThisLock)
            {
                boolean isTargetLock = targetLock.tryLock();
                try {
                    if (isTargetLock){
                        if (this.balance>=transferMoney){
                            this.balance -= transferMoney;
                            target.balance += transferMoney;
                        }
                    }
                } finally {
                    targetLock.unlock();
                }
            }
        } finally {
            thisLock.unlock();
        }
    }
    public static void main(String[] args) {

    }
}
