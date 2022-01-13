package cn.han.design_pattern.factory3;

public class XiaoMiFactory implements Factory{
    @Override
    public Phone getPhone() {
        return new XiaoMiPhone();
    }

    @Override
    public Computer getComputer() {
        return new XioaMIComputer();
    }
}
