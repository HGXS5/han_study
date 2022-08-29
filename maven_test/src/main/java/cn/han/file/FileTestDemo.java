package cn.han.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author han_s
 * @Date 2022/6/8 9:33
 * @ProName maven_test
 */
@Slf4j
public class FileTestDemo {
    public static void main(String[] args) throws IOException {
//        String aaa = new File("aaa").getAbsolutePath();
//        String excelExportPath = "D:\\springboot_han\\han_file_image_audio_video\\excelAll";
//        File file = new File(excelExportPath, "demo.txt");
//        String aaa = file.getAbsolutePath();
//        if (file.exists()) {
//            System.out.println("excel");
//        }else {
//            file.createNewFile();
//            System.out.println("creat");
//        }
//        String aaa = FileTestDemo.class.getResource("/").getPath();
//        System.out.println(aaa);
        String path = "ftp://ftpuser:Wasu#123cyx@125.210.161.206//www/ftpuser/artStar//nfs/video/1648795457714_484.mp4";
//        int i = path.indexOf("//");
//        String substring = path.substring(i+2);
//
//        System.out.println(substring);
////        while (substring.length()>0){
////            int i1 = substring.indexOf("//");
////            String substring1 = substring.substring(i1);
////
////        }
//
//
//
//        Pattern p = Pattern.compile("//");
//        Matcher m = p.matcher(path);
//        int count =0;
//        String re = "//";
//        while (m.find()){
//            if (count>0){
//                int start = m.start();
//                String substring1 = path.substring(0, start);
//                System.out.println(substring1);
//                System.out.println(start);
//                String strPath = path.substring(start+re.length());
//                System.out.println(strPath);
//                path = substring1 + "/" + strPath;
////                char c = path.charAt(start);
////                System.out.println(c);
////                path.replaceAll(String.valueOf(c), "/");
//                System.out.println(path);
//            }
//            count++;
//
//        }
//        StringBuffer buffer = new StringBuffer();
//
//        String[] strings = path.split("//");
//        for (int i = 0; i < strings.length; i++) {
//            if (i==0){
//                String str = strings[i];
//                System.out.println(str);
//                buffer.append(str + "//");
//            }
//            if (i>0&&i==strings.length-1){
//                String str = strings[i];
//                System.out.println(str);
//                buffer.append(str);
//                break;
//            }
//            if (i>0){
//                String str = strings[i];
//                System.out.println(str);
//                buffer.append(str + "/");
//            }
//        }
//        System.out.println(buffer.toString());
        String s = ftpChangeUrl(path);
        System.out.println(s);

    }
    private static String ftpChangeUrl(String ftpUrl) {
//        logger.info("修改前ftpIP:{}", ftpUrl);
        StringBuffer buffer = new StringBuffer();

        StringBuilder ftpNewPath = new StringBuilder();
        String[] split = ftpUrl.split("//www");
        for (String s : split) {
            ftpNewPath.append(s);
        }
        String[] strings = ftpNewPath.toString().split("//");
        for (int i = 0; i < strings.length; i++) {
            if (i == 0) {
                String str = strings[i];
                buffer.append(str + "//");
            }
            if (i > 0 && i == strings.length - 1) {
                String str = strings[i];
                buffer.append(str);
                break;
            }
            if (i > 0) {
                String str = strings[i];
                buffer.append(str + "/");
            }
        }
//        logger.info("修改后ftpIP:{}", buffer.toString());
        String pathAs = buffer.toString();
        String[] split1 = pathAs.split("@");
        String s = split1[1];
        String ftpUser = "ftp://ftpuser01:1qaz)OKM@";
        String allPath = ftpUser + s;
        return allPath;
    }


}
