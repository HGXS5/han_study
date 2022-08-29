package cn.han.string;

/**
 * @Author han_s
 * @Date 2022/8/4 10:35
 * @ProName maven_test
 */
public class StringTestDemo {
    public static void main(String[] args) {
        test3();
//        int a = 2;
//        System.out.println(++a);
//        System.out.println(a++);
//        short s1 = 1;
//        s1 += 1;
////        s1 = s1 + 1;
//        System.out.println(s1);
    }
    public void test1(){
        String time = "1000";
        Integer integer = Integer.valueOf(time);
        int value = integer;
        System.out.println(integer);
    }
    public static void test2(){
        String methodName = "getName";
        int index = methodName.indexOf("get");
        String filedMe = methodName.substring(index+3).toLowerCase();
        System.out.println(filedMe);
    }
    public static void test3(){
        String videoUrl = "https://wasu-hls-test-input.obs.cn-east-2.myhuaweicloud.com/gxyk/89961649321688955";
        String urlNew = "https://itvdev.wasu.cn/wasu-zhjtsyb-ugc-east/";
        String urlOld = "https://wasu-hls-test-input.obs.cn-east-2.myhuaweicloud.com/";

        if (videoUrl.contains(urlOld)) {
            StringBuffer sb = new StringBuffer();
            sb.append(urlNew);
            String part = videoUrl.substring(urlOld.length());
            System.out.println(part);
            sb.append(part);
            System.out.println(sb.toString());

        }

    }
}
