package cn.han.reflection;

import cn.han.workdemo.CyxClient;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;

/**
 * @Author han_s
 * @Date 2022/9/4 12:48
 * @ProName maven_test
 */
@Data
@Getter
public class AnnoReflection {



    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("cn.han.workdemo.CyxClient");
        CyxClient cyxClient = (CyxClient) aClass.newInstance();

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            String typeName = fieldType.getName();
            System.out.println(typeName);
            if (!"logger".equals(field.getName())&&"java.lang.String".equals(typeName)) {
                field.set(cyxClient,"qwew");
            }
        }
        System.out.println(cyxClient.toString());
//        Annotation[] annotations = aClass.getAnnotations();
//        Annotation[] declaredAnnotations = aClass.getDeclaredAnnotations();
//        for (Annotation annotation : annotations) {
//            Service service = aClass.getAnnotation(Service.class);
//            System.out.println(annotation.annotationType());
//
//            System.out.println(annotation.getClass().getName());
//        }
    }
}
