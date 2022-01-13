package cn.han.design_pattern.factory1;

public class XiaoMiFactory implements FactoryPhone{
    @Override
    public Phone createPhone() {
        XiaoMiPhone xiaoMiPhone = new XiaoMiPhone();
        return xiaoMiPhone;
    }
}
