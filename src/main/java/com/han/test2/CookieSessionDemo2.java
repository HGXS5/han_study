package com.han.test2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/day/session2")
public class CookieSessionDemo2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //session2:获得session1传递过来的ID
        //需求：从session容器中取出IP
        //获得session对象
          HttpSession session = req.getSession();
        //获取IP地址
          String ip = (String) session.getAttribute("ip");
        System.out.println("ip"+ip);
        //将IP打印到浏览器中
        resp.setContentType("test/html;charset=utf-8");
        resp.getWriter().println("ip:"+ip);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
