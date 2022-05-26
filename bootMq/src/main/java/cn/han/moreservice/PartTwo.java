package cn.han.moreservice;

import org.springframework.stereotype.Service;

/**
 * @Author han_s
 * @Date 2022/5/16 9:03
 * @ProName maven_test
 */
@Service("two")
public class PartTwo implements PartTop{
    @Override
    public void play() {
        System.out.println("partTwo.......");
    }
}
