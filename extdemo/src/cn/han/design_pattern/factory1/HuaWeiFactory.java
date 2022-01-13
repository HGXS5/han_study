package cn.han.design_pattern.factory1;

public class HuaWeiFactory implements FactoryPhone {
    @Override
    public Phone createPhone() {
        return new HuaWeiPhone();
    }
}
