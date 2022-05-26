package cn.han.rabbitmq.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author han_s
 * @Date 2022/4/2 15:43
 * @ProName bootMq
 */
@RestController
public class TestAop {

    @GetMapping("/log")
    @ResponseBody
    public String testLog(){
        System.out.println("hello world");
        return "hello world";
    }
}
