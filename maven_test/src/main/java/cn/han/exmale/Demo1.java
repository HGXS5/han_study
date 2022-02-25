package cn.han.exmale;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Demo1 {
    // 用 1,2 , 2 ,3, 4 ,5 这 6 个数字, 用 Java 写一个 main 函数, 打印出所有不同的排列, 如:
    // 512234, 412345 等, 要求: “4”不能在第三位, “3”与”5”不能相连
    public static void main(String[] args) {
        String[] arrOld = {"1", "2", "2", "3", "4", "5"};
        String result = "";
        for (int i = 0; i < arrOld.length; i++) {
            result = result + arrOld[i];
        }


        System.out.println(result.substring(0, arrOld.length - 1));
    }

    public void sortTest() {

    }

    @Test
    public void test() {

//        StringBuffer a = new StringBuffer("A");
//        StringBuffer b = new StringBuffer("B");
//        operator(a, b);
//        System.out.println(a + "," + b);
        System.out.println(foo(5));
    }
    public int foo(int n){
        if(n<2) {
            return n;
        }
        return foo(n-1)+foo(n-2);
    }
    public void operator(StringBuffer x, StringBuffer y) {
        x.append(y);
        y = x;
    }
}