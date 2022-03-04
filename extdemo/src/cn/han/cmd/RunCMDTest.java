package cn.han.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunCMDTest {
   static Process exec ;
    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("grep 2222 test.txt");
        cmdTest(sb.toString());

        if (exec!=null){
            InputStream inputStream = exec.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line =null;
            while((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            System.out.println(stringBuffer.toString());
        }

    }
    public static void cmdTest(String cmd) throws IOException {
       exec = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",cmd});
    }
}
