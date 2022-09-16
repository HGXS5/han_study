package cn.han.reflection;

/**
 * @Author han_s
 * @Date 2022/9/8 15:23
 * @ProName maven_test
 */
public class WhileTest {
    public static void main(String[] args) {
        String s = test1();
        System.out.println(s);
    }

    public static String test1(){
        while (true){
            if (true){
                throw new RuntimeException();
            }
            return "test";
        }
    }
}
