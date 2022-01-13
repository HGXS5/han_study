package cn.han.design_pattern.factory3;

public class HuaWeiFactory implements Factory{
    @Override
    public Phone getPhone() {
        return new HuaweiPhone();
    }

    @Override
    public Computer getComputer() {
        return new HuaweiComputer();
    }
}
