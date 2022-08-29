package cn.han.design_pattern.strategy;

import cn.han.design_pattern.strategy.impl.StrategyOneImpl;

/**
 * @Author han_s
 * @Date 2022/8/11 10:45
 * @ProName maven_test
 */
public class StrategyDemo {
    public static void main(String[] args) {
        InductionContext context = new InductionContext(new Strategy() {
            @Override
            public Integer calculate(int num1, int num2) {
                return num1*num2;
            }
        });
        System.out.println(context.executeStrategy(2, 3));
        InductionContext context1 = new InductionContext(new StrategyOneImpl());
        System.out.println(context1.executeStrategy(3, 2));
    }
}
