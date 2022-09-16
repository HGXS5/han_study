package cn.han.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @Author han_s
 * @Date 2022/8/29 15:45
 * @ProName maven_test
 */
@Component
public class HanPointcuts {
    public void addOnly(){
        System.out.println("addOnly执行。。。。");
    }
    public void addAll(){
        System.out.println("addAll执行。。。。");
    }
    public String deleteOnly(){
        System.out.println("deleteOnly执行。。。。");
        return "删除完成";
    }
    public void updateOnly(){
        System.out.println("updateOnly执行。。。。");
    }
}
