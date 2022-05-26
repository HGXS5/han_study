package cn.han.demo;

/**
 * @Author han_s
 * @Date 2022/4/4 10:09
 * @ProName bootMq
 */
public class DemoOne {
    public static void main(String[] args) {
        System.out.println(test(10));
    }

    public static int test(int n) {
        if (n==1 || n==2){
            return n;
        }
        return test(n - 2)+test(n-1);
    }
}
