package cn.han.rabbitmq.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author han_s
 * @Date 2022/4/2 15:42
 * @ProName bootMq
 */
@Aspect
@Component
public class LogAop {
    private static Logger log = LoggerFactory.getLogger(LogAop.class);

    public static void main(String[] args) {
        String string = "hanlibo";
        char[] chars = string.toCharArray();
        int s = chars[0] - chars[1];
        System.out.println(chars[0]);
        System.out.println(s);
    }

//    @Pointcut("execution(public * cn.han.rabbitmq.aop.TestAop(..))")
//    public void test(){}
//
//    @Before("test()")
//    public void beforeLog(Joinpoint joinpoint){
//        log.info("before:"+"执行了。。。");
//    }
//    @After("test()")
//    public void afterLog(Joinpoint joinpoint){
//
//        log.info("after:"+"执行了。。。");
//    }
//    @AfterReturning("test()")
//    public void afterReturnLog(Joinpoint joinpoint){
//
//        log.info("afterReturnLog:"+"执行了。。。");
//    }
////    @AfterThrowing("test()")
////    public void throwLog(Joinpoint joinpoint){
////
////        log.info("throwLog:"+"执行了。。。");
////    }
////    @Around("test()")
////    public void aroundLog(Joinpoint joinpoint){
////        log.info("aroundLog:"+"执行了。。。");
////    }
}
