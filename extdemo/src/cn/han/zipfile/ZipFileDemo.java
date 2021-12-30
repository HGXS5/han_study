package cn.han.zipfile;

import com.sun.deploy.net.HttpRequest;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileDemo {
    public static void main(String[] args) {
        String uploadPath = "D:\\han\\upload\\";
        System.out.println(uploadPath);
    }

     public File uploadTest(HttpServletRequest request){
         String separator = File.separator;
         String uploadPath = "D:\\han\\upload\\";
         File fileUpload = null;
        File uploadPathF = new File(uploadPath);
        if (!uploadPathF.exists()){
            uploadPathF.mkdirs();
        }
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置保存到内存大小
            factory.setSizeThreshold(1024*1024*10);
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置最大上传文件大小
            upload.setSizeMax(1024*1024*50);
            //解析请求上传文件
            List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(request));
            Iterator<FileItem> iterator = fileItems.iterator();
            while(iterator.hasNext()){
                FileItem item = iterator.next();
                if (!item.isFormField()){
                    fileUpload = new File(uploadPath + item.getName());
                    item.write(fileUpload);
                }
            }
            return fileUpload;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
     void zipFileTest(){
        String zipFilePath = "C:\\Users\\dell\\Downloads\\commons-io-2.11.0-bin.zip";
        String path = "D:\\han\\zipFile\\";

        if (!zipFilePath.isEmpty()){
            InputStream inputStream = null;
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            try {
                File f = new File(path);
                if (!f.exists()){
                    f.mkdirs();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                System.out.println("开始压缩："+sdf.format(System.currentTimeMillis()));

                ZipFile zf = new ZipFile(zipFilePath);
                Enumeration<? extends ZipEntry> entries = zf.entries();
                while (entries.hasMoreElements()){
                    ZipEntry entry = entries.nextElement();
                    if (entry.isDirectory()){
                        File directoryZipPath = new File(path + entry.getName().substring(0, entry.getName().length() - 1));
                        directoryZipPath.mkdirs();
                    }else {
                        //获取该压缩包中文件的输入流
                        inputStream = zf.getInputStream(entry);
                        //创建文件夹及文件
                        File fileZipPath = new File(path+entry.getName());
                        if (!fileZipPath.exists()&&fileZipPath.isDirectory()){
                            fileZipPath.mkdirs();
                        }
                        fileZipPath.createNewFile();

                        //设置可执行权限
                        fileZipPath.setExecutable(false);
                        //设置可读权限
                        fileZipPath.setReadable(false);
                        //设置可写权限
                        fileZipPath.setWritable(true);

                         fos = new FileOutputStream(fileZipPath);
                        //获得每个文件的输入流对象
                         bos = new BufferedOutputStream(fos);
                        //进行数据转移
                        int read;
                        while((read = inputStream.read())!=-1){
                            bos.write(read);
                        }
                        bos.flush();

                    }
                }
                System.out.println("压缩完成："+sdf.format(System.currentTimeMillis()));
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (inputStream!=null){inputStream.close();}
                    if (fos!=null){fos.close();}
                    if (bos!=null){bos.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
