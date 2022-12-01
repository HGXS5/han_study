package cn.han.Id;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * @Author han_s
 * @Date 2022/11/21 11:11
 * @ProName maven_test
 */
public class IdDemo {
    public static void main(String[] args) {
//        int i2 = RandomUtil.randomInt();
//        System.out.println(i2);
        System.out.println(System.currentTimeMillis()-Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);

        for (int i = 0; i <100 ; i++) {
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid.hashCode());
//            System.out.println(RandomUtil.randomInt());
//            System.out.println(RandomUtil.randomLong());
//            System.out.println(RandomUtil.randomBigDecimal());
//            System.out.println(IdUtil.randomUUID());
//            System.out.println(RandomUtil.randomInt(0,10));
//            System.out.println(System.currentTimeMillis()/1000);
        }
    }
}
