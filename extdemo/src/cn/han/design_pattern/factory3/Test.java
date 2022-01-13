package cn.han.design_pattern.factory3;

/**
 * 工厂方法是针对不同企业对单一产品的实现（比如可口可乐和百事可乐都是要生产可乐 ）
 * 抽象工厂是针对不同企业对多种产品的实现（华为和小米都要生产电脑和手机 ）
 *
 * 两种实现方式相似，只是针对不同业务场景进行使用
 */
public class Test {
    public static void main(String[] args) {
        Factory f = new XiaoMiFactory();
        f.getPhone().phone();
        f.getComputer().computer();
    }
}
