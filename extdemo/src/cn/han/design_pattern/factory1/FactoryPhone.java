package cn.han.design_pattern.factory1;

/**
 * 工厂方法:实例化推迟到子类，由子类决定要实例化的类是哪一个
 */
public interface FactoryPhone {
    Phone createPhone();
}
