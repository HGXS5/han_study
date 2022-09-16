package cn.han.field;

import java.util.Arrays;

/**
 * @Author han_s
 * @Date 2022/9/6 10:34
 * @ProName maven_test
 */
public class FileIdTest {
    public static void main(String[] args) {
        FileId fileId = new FileId();
        String[] strs = new String[3];
        strs[0] = "7";
        strs[1] = "8";
        String[] test = fileId.test();
        System.out.println("test："+ Arrays.toString(test)+"--- fileID："+Arrays.toString(fileId.getString()));
    }
}
