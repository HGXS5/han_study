package cn.han.objectfg;

import cn.han.util.Constant;
import cn.han.util.InjectStatusConstant;
import cn.han.workdemo.PropertyManager;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.errors.ApiException;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.proxy.Proxy;

import java.util.Date;
import java.util.Map;

/**
 * @Author han_s
 * @Date 2022/7/11 17:41
 * @ProName maven_test
 */
public class DemoTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String qualifiedClassName = "cn.han.objectfg.YoukuSpAssetFile";
        Class<?> clz = Class.forName(qualifiedClassName);
        String beanName = StringUtils.uncapitalize(clz.getSimpleName());
        System.out.println(beanName);
    }
}
