package cn.han.tomcat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author han_s
 * @Date 2022/5/9 14:05
 * @ProName maven_test
 */

public class TomcatDemo {
    public static void main(String[] args) {

        String userDir = System.getProperty("user.dir");
        System.out.println(userDir);
        System.out.println(System.getSecurityManager());
        String temp = System.getProperty("java.io.tmpdir");
        System.out.println(temp);
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
        System.getProperty(javax.naming.Context.URL_PKG_PREFIXES);
        test();
        String className = System.getProperty("org.apache.tomcat.util.digester.PROPERTY_SOURCE");
        System.out.println(className);
    }

    private static void test(){
        HashMap<Class<?>, List<String>> fakeAttributes = new HashMap<>();
        ArrayList<String> attrs = new ArrayList<>();
        attrs.add("className");
        fakeAttributes.put(TomcatDemo.class, attrs);
        System.out.println(fakeAttributes.toString());
        System.out.println(fakeAttributes.get(Object.class));
    }
    protected String replace(String str) {
        String result = str;
        int pos_start = str.indexOf("${");
        if (pos_start >= 0) {
            StringBuilder builder = new StringBuilder();

            int pos_end;
            for(pos_end = -1; pos_start >= 0; pos_start = str.indexOf("${", pos_end + 1)) {
                builder.append(str, pos_end + 1, pos_start);
                pos_end = str.indexOf(125, pos_start + 2);
                if (pos_end < 0) {
                    pos_end = pos_start - 1;
                    break;
                }

                String propName = str.substring(pos_start + 2, pos_end);
                String replacement = null;
                if (propName.length() == 0) {
                    replacement = null;
                } else if ("catalina.home".equals(propName)) {
//                    replacement = getCatalinaHome();
                } else if ("catalina.base".equals(propName)) {
//                    replacement = getCatalinaBase();
                } else {
                    replacement = System.getProperty(propName);
                }

                if (replacement != null) {
                    builder.append(replacement);
                } else {
                    builder.append(str, pos_start, pos_end + 1);
                }
            }

            builder.append(str, pos_end + 1, str.length());
            result = builder.toString();
        }

        return result;
    }

}
