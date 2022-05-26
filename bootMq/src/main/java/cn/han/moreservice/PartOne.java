package cn.han.moreservice;

import org.springframework.stereotype.Service;

/**
 * @Author han_s
 * @Date 2022/5/16 9:02
 * @ProName maven_test
 */
@Service("one")
public class PartOne implements PartTop {
    @Override
    public void play() {
        System.out.println("partOne.......");
    }
}
