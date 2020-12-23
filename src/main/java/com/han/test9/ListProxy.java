package com.han.test9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 *1. InvocationHandler 接口  它是由代理实例的调用处理流程序实现的接口，每个代理实例都有一个关联的调用处理程序。
 *                          当在代理实例上调用方法时，该方法调用将被编码并分配到其调用处理程序的invoke方法。
 *2. invoke（Object Proxy， Method method, Object[] args) 方法 在代理实例上处理方法调用并返回结果。
 *                                                      在与其关联的代理实例上调用方法时，将在调用处理程序上调用该方法。
 *                                    参数：、
 *                                      proxy 在其上调用方法的代理实例
 *                                      method 在与代理实例上调用的接口方法相对应的Method实例，
 *                                              Method对象的声明类将是在其中声明该方法的接口，
 *                                              它可能是代理类通过其继承该方法的代理接口的超接口。
 *                                      args 包含在代理实例上的方法调用中传递的参数值的对象数组；如果接口方法不带参数，则为Null.
 *                                             基本类型的参数包装在适当的基本包装器类的实例中
 *                                    返回：
 *                                      从代理实例上的方法调用返回的值。如果接口方法的声明的返回类型时原始类型，则此方法返回的值必须是对应的原始包装类型的实例。
 *                                      否则返回true。
 *                                      否则，它必须是可分配给声明的返回类型的类型。
 *                                      如果此方法返回的值为null，并且接口方法的返回类型为原始类型，则代理实例上的方法调用将抛出NullPointerException.
 *                                      如果此方法返回的值与上述接口方法的声明的返回类型不兼容，则对代理实例的方法调用将抛出ClassCastException.
 *3. invoke(Object obj, Object... args)方法（method中） 在具有指定参数的指定对象上调用此Method对象表示的基础方法
 *                                                      各个参数将自动解包以匹配原始式参数，并且原始参数和引用参数都必须根据需要进行方法调用转换
 *                                                      如果基础方法是静态的，则忽略指定的obj参数。它可以为空
 *                                                      如果基础方法所需的形式参数数量为0，则提供的args数组的长度可以为0或为null。
 *                                                      如果基础方法是静态的，则声明该方法的类将被初始化（如果尚未初始化）
 *                                      参数：
 *                                          obj 从中调用基础方法的对象。
 *                                          args 用于方法调用的参数。
 *                                      返回：
 *                                          使用args参数在obj上分派此对象表示的方法的结果。
 */
public class ListProxy<T> implements InvocationHandler {
    private List<T> target;

    public ListProxy(List<T> target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        /**
         * 1. invoke(
         */
        Object retVal = null;
        System.out.println("["+method.getName()+":"+args[0]+"]");
         retVal = method.invoke(target, args);
        System.out.println("[size = "+target.size()+"]");
        return retVal;
    }
}
