package cn.han.io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author han_s
 * @Date 2022/4/21 11:08
 * @ProName maven_test
 */
public class FileTest {
    static String path = "D:\\huashu";
    public static void main(String[] args) {
        fileDemo2();
    }

    public  static void fileDemo1(){
        File file = new File(path);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) {
                String filename = file1.getName();
                String name = filename.substring(filename.lastIndexOf(".") + 1);
                path = path + format.format(new Date());
                File file2 = new File(path);
                if (!file2.exists()){
                    file2.mkdir();
                }
                System.out.println(file2.getName());
                System.out.println(name);
            }
        }
    }
    private static void fileDemo2(){
        File oldName = new File("D:\\huashu\\artStar_3455.imp");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(new Date());
        System.out.println(format1);
        String separator = File.separator;
        System.out.println(File.separator);
        path = path + separator+format1;
        File d = new File(path);
        if (!d.exists()){
            d.mkdir();
        }
        System.out.println(path);
        String allPath = path + separator + "artStar_3455.imp" + ".error";
        System.out.println(allPath);
        File file = new File(allPath);

        boolean b = oldName.renameTo(file);

    }

    private static  void renameToDemo(){

    }
}
