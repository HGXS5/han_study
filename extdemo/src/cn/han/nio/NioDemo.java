package cn.han.nio;

import javafx.scene.input.DataFormat;
import sun.misc.VM;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NioDemo {
    public static void main(String[] args) {
        simpleBuffer();
    }

    /**
     * nio将通道数据读取到缓存区
     *
     * @return
     */
    static ByteBuffer readByteBuffer() {
        File file = null;
        FileInputStream fi = null;
        try {
            file = new File("D:\\han\\extdemo\\src\\cn\\han\\nio\\nio.txt");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            System.out.println("文件最后一次被修改时间:" + sdf.format(file.lastModified()));

            fi = new FileInputStream(file);

            //内存分配申请
            ByteBuffer allocate = ByteBuffer.allocate(1024);//
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024);//

            //获取通道对象Channel
            FileChannel channel = fi.getChannel();

            //将数据从通道读取到缓存区中
            int read = channel.read(allocate);
            System.out.println(read);

            System.out.println("文件最后一次被修改时间:" + sdf.format(file.lastModified()));

            return allocate;

        } catch (Exception e) {
            try {
                file.createNewFile();
            } catch (Exception ioException) {
                System.out.println(ioException.getMessage());
            }
            System.out.println(e.getMessage() + "-------创建该文件:" + file.getName());
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * nio将缓存数据写入通道中
     *
     * @return
     */
    static ByteBuffer writeByteBuffer() {
        File file = null;
        FileOutputStream fou = null;
        try {
            file = new File("D:\\han\\extdemo\\src\\cn\\han\\nio\\nio.txt");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            System.out.println("文件最后一次被修改时间:" + sdf.format(file.lastModified()));

            fou = new FileOutputStream(file);

            //创建固定数据
            byte[] bytes = "1234asdfHn航歌功颂德nio".getBytes();
            //内存分配申请
            ByteBuffer allocate = ByteBuffer.allocate(1024);//
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024);//

            //获取通道对象Channel
            FileChannel channel = fou.getChannel();
            //将固定数据存放到开辟的缓存内
            for (byte aByte : bytes) {
                allocate.put(aByte);
            }
            //将缓存区数据写入到通道中
            allocate.flip();
            int write = channel.write(allocate);
            System.out.println(write);

            System.out.println("文件最后一次被修改时间:" + sdf.format(file.lastModified()));
            return allocate;
        } catch (Exception e) {
            try {
                file.createNewFile();
            } catch (Exception ioException) {
                System.out.println(ioException.getMessage());
            }
            System.out.println(e.getMessage() + "-------创建该文件:" + file.getName());
        } finally {
            try {
                if (fou != null) {
                    fou.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static void simpleBuffer() {
        File filefi = null;
        File filefo = null;
        InputStreamReader ir = null;
        OutputStreamWriter ow = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            filefo = new File("D:\\han\\extdemo\\src\\cn\\han\\nio\\io.txt");
            filefi = new File("D:\\han\\extdemo\\src\\cn\\han\\nio\\han.txt");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            System.out.println("文件最后一次被修改时间:" + sdf.format(filefo.lastModified()));
            System.out.println(filefi.getAbsolutePath());
            System.out.println(filefi.getCanonicalPath());
            fi = new FileInputStream(filefi);
            fo = new FileOutputStream(filefo);
            ir = new InputStreamReader(fi,"gbk");
            br = new BufferedReader(ir);

            ow = new OutputStreamWriter(fo,"gbk");
            bw = new BufferedWriter(ow);
            //
            int read ;
            while ((read=br.read())!=-1){
                bw.write(read);
            }
            bw.flush();
            System.out.println("文件最后一次被修改时间:" + sdf.format(filefo.lastModified()));

        } catch (Exception e) {
            try {
                filefo.createNewFile();
            } catch (Exception ioException) {
                System.out.println(ioException.getMessage());
            }
            System.out.println(e.getMessage() + "-------创建该文件:" + filefo.getName());
        } finally {
            try {
                fi.close();
                fo.close();
                ir.close();
                br.close();
                ow.close();
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
