package cn.han.design_pattern.strategy;

/**
 * @Author han_s
 * @Date 2022/8/11 10:37
 * @ProName maven_test
 */
public class InductionContext {

    Strategy strategy;

    public InductionContext(Strategy strategy) {
        this.strategy = strategy;
    }
    public int executeStrategy(int num1, int num2){
        return strategy.calculate(num1, num2);
    }
}
