package com.han.test7;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //可用request.getPart（)方法获得名为photo的上传附件
        //也可用request.getParts()获得所有上传附件（多文件上传）
        //然后通过循环分别处理每一个上传的文件
            Part photo = req.getPart("photo");
        if (photo != null && photo.getSubmittedFileName().length() > 0){
            //用ServletContext对象的getRealPath()方法获得上传文件夹的绝对路径
            String savePath = req.getServletContext().getRealPath("/upload");
            //Servlet 3.1规范中可以用Part对象的getSubmittedFileName()方法获得上传的文件名
            //更好的做法是为上传的文件进行重命名（避免同名文件的相互覆盖）
            photo.write(savePath + "/" + photo.getSubmittedFileName());
            req.setAttribute("hint","Upload Successfully!");
        }else {
            req.setAttribute("hint", "Upload failed");
        }
        //跳转回到上传页面
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
