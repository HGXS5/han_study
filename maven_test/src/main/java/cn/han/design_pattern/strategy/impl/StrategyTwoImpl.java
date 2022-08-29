package cn.han.design_pattern.strategy.impl;

import cn.han.design_pattern.strategy.Strategy;

/**
 * @Author han_s
 * @Date 2022/8/11 10:36
 * @ProName maven_test
 */
public class StrategyTwoImpl implements Strategy {
    @Override
    public Integer calculate(int num1, int num2) {
        return num1-num2;
    }
}
