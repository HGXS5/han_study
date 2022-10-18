package cn.han.spring.aop;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @Author han_s
 * @Date 2022/8/29 15:48
 * @ProName maven_test
 */
@Aspect
@Component
public class HanAspect {

    @Before(value = "execution(* cn.han.spring.aop.HanPointcuts.add*(..))")
    public void add(){
        System.out.println("before......");
    }
    @SneakyThrows
    @Around(value = "execution(* cn.han.spring.aop.HanPointcuts.update*(..))")
    public String update(ProceedingJoinPoint pjp){
        System.out.println("Around......");
        pjp.proceed();
        System.out.println("执行完方法");
        return pjp.getKind();
    }
    @AfterReturning(pointcut = "execution(* cn.han.spring.aop.HanPointcuts.delete*(..))",returning = "s")
    public void delete(String s){
        System.out.println("afterReturning....");
        System.out.println(s);
    }
}
