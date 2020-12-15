package com.han.test4;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 上下文监听器，在服务端启动时初始化onLineCount和maxOnLineCount两个变量
 * 并将其置于服务器上下文（ServletContext）中，初始值都是0
 */
@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("onLineCount",0);
        servletContextEvent.getServletContext().setAttribute("maxOnLineCount",0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
