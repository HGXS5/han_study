package cn.han.strin;

import java.io.File;

public class StringTestDemo {
    public static void main(String[] args) {
//        StringBuffer sb = new StringBuffer();
//        String s = "a,b,c,d";
//        String[] split = s.split(",",1);
//        for (String s1 : split) {
//            System.out.println(s1);
//
//        }
//        String filePath = "D:\\han\\itapp\\xcEduService01\\test-freemarker\\src\\main\\resources\\templates\\test1.ftl";
//        //获取文件名称
//        int i = filePath.lastIndexOf("\\");
//        int i1 = filePath.lastIndexOf(".");
//        String substring = filePath.substring(i+1,i1);
//        System.out.println(substring);
        String filePath = "D:\\han\\itapp\\xcEduService01\\test-freemarker\\src\\main\\resources\\templates\\test1.ftl";
        File f = new File(filePath);
        System.out.println(f.getName());
    }

}
