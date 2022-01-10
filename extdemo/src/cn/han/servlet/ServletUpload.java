package cn.han.servlet;

import cn.han.zipfile.ZipFileDemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class ServletUpload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("gbk");

        ZipFileDemo zfd = new ZipFileDemo();
        File file = zfd.uploadTest(req);
        if (file.exists()) {
            System.out.println(file.getName());
        }
        System.out.println("run.....");
    }
}
