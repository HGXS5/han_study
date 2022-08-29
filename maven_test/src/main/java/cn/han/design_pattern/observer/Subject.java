package cn.han.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author han_s
 * @Date 2022/8/15 10:47
 * @ProName maven_test
 * 被观察者
 */
public class Subject {
    /*方法功能
     *被观察者对象
     * 1.存放观察者得集合---list集合，存放观察者
     * 2.绑定观察者---存入集合
     * 3.解除绑定---从集合中移除
     * 4.发生改变---例如当前的被观察者某个属性值，被修改时，就会通知观察者
     * 5.通知观察者执行---在被观察者发生改变时，就会通知集合中的每一个观察者
     *
     * 接口---概述不同物体的特征 has的关系  如：人、火车、牛、水
     * 抽象类---表示类似物体的特征 is的关系  如：鸟、鱼、马
     *
     *为观察者提取统一的接口或者抽象类
     *   在这里使用接口或者抽象类的目的，在被观察者对象中能够统一管理。
     *   --集合中只存入接口或者抽象类，在真正实操时，既可以填写观察者的具体对象也可以填写接口或者抽象
     *   --在被观察者对象中执行通知时，可以直接使用接口或者抽象类调用方法
     *
     * 观察者实现或者继承--接口或者抽象类，重写方法执行不同逻辑
     * 实例化对象时，在需要将该对象绑定到被观察者对象中的集合中
     *   --在构造器中执行绑定操作
     *   --
     * */
    private Observer observer;
    private List<Observer> observers = new ArrayList<>();
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        noticeObservers();
    }

    private void noticeObservers() {
        for (Observer observer1 : observers) {
            observer1.update();
        }
    }

    public void bindSubject(Observer observer){
       observers.add(observer);
       System.out.println("绑定");
   }
}
