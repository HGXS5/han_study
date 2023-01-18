package cn.han.operation;

import java.util.Arrays;

/**
 * @Author han_s
 * @Date 2023/1/12 14:09
 * @ProName maven_test
 */
public class BitwiseOperation {
    private static final long WORD_MASK = 0xffffffffffffffffL;
    private static long[] words = new long[2];
    public static void main(String[] args) {
        System.out.println("------"+WORD_MASK);
        int a = 128;
        int result1 = (a-1) >> 6;
        System.out.println(result1);
        int b = 0;
        int result2 = b >> 6;
        System.out.println(result2);
        long result3 = WORD_MASK << b;
        System.out.println(result3);
        long result4 = WORD_MASK >>> -a;
        System.out.println(result4);
        long result5 = result3 & result4;
        System.out.println(result5);
        words[b] |= result5;
        System.out.println(Arrays.toString(words));

        for (int i = b+1; i < ; i++) {
            
        }
    }
}
