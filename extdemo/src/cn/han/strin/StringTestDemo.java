package cn.han.strin;

public class StringTestDemo {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        String s = "a,b,c,d";
        String[] split = s.split(",",1);
        for (String s1 : split) {
            System.out.println(s1);

        }
    }

}
