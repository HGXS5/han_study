package cn.han.regx;

import jdk.nashorn.internal.ir.CallNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String str = "a123|qsfdfd_12345_k 5";
        String regex ="//^//";

        Pattern compile = Pattern.compile(regex);
        String pattern = compile.pattern();
        System.out.println("正则格式："+pattern);

        Matcher matcher = compile.matcher(str);
        if (matcher.find()){
            String group = matcher.group();
            System.out.println(group);
        }



    }
}
