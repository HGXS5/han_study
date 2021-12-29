package cn.han.strin;

public class StringTestDemo {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        String s = "12,123,124";
        String[] split = s.split(",");
        for (String s1 : split) {
            sb.append(s1).append(s1);
        }
        System.out.println(sb.toString());

    }
}
