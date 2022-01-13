package cn.han.design_pattern.factory3;

/**
 * 抽象工厂是针对产品族
 */
public interface Factory {
    Phone getPhone();

    Computer getComputer();
}
