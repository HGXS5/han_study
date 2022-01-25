package cn.han.annotation;

import java.lang.annotation.*;

/*
* 注解是什么？
*   1.注解是一种元数据类型，即注解是属于java的一种数据类型，和类、接口、数组、枚举类似
*   2.注解用来修饰，类、方法、变量、参数、包。
*   3.注解不会对所修饰的代码产生直接影响
* 注解基本作用
*   1.生成文档
*   2.代替配置文件的功能
*   3.编译时进行格式检查
*注解与XMl区别
*   1.注解：是一种分散式的元数据，与源代码耦合
*   2.xml：是一种集中式的元数据，与源代码解耦
* 注解缺点
*   1.因为会分散到很多类中所以不好管理和维护
*   2.如果需要更改会改变源代码
*   3.缺乏灵活性
*   4.缺乏可扩展性和复杂性
* 注解缺点
*   1.注解比较方便
*   2.简化配置提高开发效率
*   3.类型安全,xml只有在运行时才能发现问题
*
* 元注解
*   Target:描述了注解修饰的对象范围，取值在ElementType枚举中定义
*       1. METHOD:用于描述方法
*       2. PACKAGE:用于描述包
*       3. PARAMETER:用于描述方法变量
*       4. TYPE:用于描述类、接口或enum类型
*   Retention:表示注解保留时间长短。取值在RetentionPolicy枚举中定义
*       1. SOURCE:在源文件中有效，编译过程中会被忽略
*       2. CLASS:随原文件一起编译在class文件中，运行时忽略
*       3. RUNTIME:在运行时有效
*
* */
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {

//    // 描述
    String actiondese() default "";

//    //类型
    Logtype actionvalue() default Logtype.LOGIN;

    //数组格式
    Logtype[] testVales();

}
