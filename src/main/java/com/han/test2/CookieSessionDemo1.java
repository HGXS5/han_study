package com.han.test2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/session1")
public class CookieSessionDemo1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //session1        //需求1：将IP保存到session中
        //获取session
        HttpSession session = req.getSession();
        //获得IP
        String ip = req.getRemoteAddr();
        //将IP保存在session中
        session.setAttribute("ip",ip);
        //需求2：手动将session对应的cookie持久化，关闭浏览器再次访问session中的数据依然存在
        //创建cookie
           Cookie cookie = new Cookie("JSESSIONID", session.getId());
        //设置cookie的最大生存时间
        cookie.setMaxAge(60*30);
        //设置有效路径
        cookie.setPath("/");
        //发送cookie
        resp.addCookie(cookie);
    //当点击的时候跳转到session2
        resp.setContentType("text/html;charset=utf-8");
        //此方法会在路径后面自动拼接sessionId
           String path = resp.encodeURL("/day/session2");
        System.out.println("path:"+path);
        //页面输出
        resp.getWriter().println("ip地址保存成功，想看 请<a href='"+ path +"'>点击<a>");
//    //重定向到session2
//        String path = resp.encodeRedirectURL("/day/session2");
//        System.out.println("重定向编码后的路径："+path);
//        resp.sendRedirect(path);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
