package cn.han.ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author han_s
 * @Date 2022/8/17 17:21
 * @ProName maven_test
 */
public class Download {
    private static final int BUFFER_SIZE = 4096;
   static String ftpUrl = "ftp://ftpuser:hanlibohan/47.100.77.11/ftp/test.txt";
   static String savePath = "D:\\test.txt";
    //ftp://ftper:ftper@125.210.136.95/gysj/光影视界/31c698984f54d26ac0dd5bbc70bbb429.mp4
    public static void main(String args[]) { // this is a function
        try {
//            boolean b = FtpUtil.get(ftpUrl, savePath);
//            if (b){
//                System.out.println("执行完");
//            }
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test1() {
        long startTime = System.currentTimeMillis();
        String ftpUrl = "ftp://47.100.77.11/ftp/test.txt";

        String file = "/ftp/test.txt";
        // name of the file which has to be download
        String host = "47.100.77.11"; // ftp server
        String user = "ftpuser"; // user name of the ftp server
        String pass = "hanlibohan"; // password of the ftp server

        ftpUrl = String.format(ftpUrl, user, pass, host);

        System.out.println("Connecting to FTP server");
        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            long filesize = conn.getContentLength();
            System.out.println("Size of the file to download in kb is:-" + filesize / 1024);
            FileOutputStream outputStream = new FileOutputStream(savePath);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("File downloaded");
            System.out.println("Download time in sec. is:-" + (endTime - startTime) / 1000);
            outputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
