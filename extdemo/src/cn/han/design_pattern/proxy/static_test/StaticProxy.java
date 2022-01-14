package cn.han.design_pattern.proxy.static_test;

/**
 * 静态代理
 */
public class StaticProxy implements People {
    private People people;

    public StaticProxy() {
        this.people = new Student();
    }

    @Override
    public void talk() {
        if (people!=null){
            if (people instanceof Student){
                System.out.println("我是代理类，我让学生说话");
                people.talk();
            }else if (people instanceof Teacher){
                System.out.println("我是代理类，我让老师说话");
                people.talk();
            }
        }

    }
}
