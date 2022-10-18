package cn.han.remote_config;

import java.util.ResourceBundle;

/**
 * @Author han_s
 * @Date 2022/9/28 15:11
 * @ProName maven_test
 */
public class RemoteConfig {
    public static void main(String[] args) {
        ResourceBundle bootstrap = ResourceBundle.getBundle("bootstrap");
        String namespaces = bootstrap.getString("namespaces");
        String[] strings = namespaces.split("\\s*[,]\\s*");
        for (String string : strings) {
            
        }
        System.out.println(bootstrap);
    }
}
