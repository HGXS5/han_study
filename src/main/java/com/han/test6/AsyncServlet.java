package com.han.test6;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. asyncSupported 声明Servlet是否支持异步操作模式
 * 2. starAsync方法 将此请求置于异步模式，
 *                  并使用原始（末包转）的ServletRequest和ServletResponse对象初始化其AsyncContext
 */
@WebServlet(urlPatterns = {"/asycn"},asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //开启Tomcat异步Servlet支持
        req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED",true);
        final AsyncContext async = req.startAsync();

        async.start(new Runnable() {
            @Override
            public void run() {
                //在此处添加异步处理的代码
                async.complete();
            }
        });
    }
}
