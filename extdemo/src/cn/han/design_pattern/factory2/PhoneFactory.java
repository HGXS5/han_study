package cn.han.design_pattern.factory2;

/**
 * 静态工厂
 */
public class PhoneFactory {

   static XiaoMiPhone creatXMPhone(){
        return new XiaoMiPhone();
    }
   static HuaWeiPhone creatHWPhone(){
        return new HuaWeiPhone();
    }

}
