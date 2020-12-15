package com.han.test4;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 会话监听，在用户会话创建和销毁的时候根据情况修改onLineCount和maxOnlineCount的值
 *
 * 1. HttpSessionListener接口 用于接收有关HttpSession生命周期更改的通知事件接口
 * 2. getSession()方法 返回更改的会话（在HttpSessionEvent类中）
 * 3. getServletContext()方法 返回此会话所属的ServletContext（在HttpSession接口中。）
 * 4. getAttribute()方法 返回具有给定名称的Servlet容器属性，如果该名称没有属性，则返回null
 * 5. setAttribute()方法 在此ServletContext中将对象绑定到给定的属性名称。（在ServletContext对象中）
 *                 如果指定名称已经用于属性，则此方法将使用新的属性替换该属性；
 *                 如果在ServletContext上配置了侦听器，则容器会相应地通知他们
 *                 如果传递了null值，则效果与调用removeAttribute()相同
 * 6.
 *
 */
@WebListener
    public class MaxCountListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext ctx = httpSessionEvent.getSession().getServletContext();
        int count = Integer.parseInt(ctx.getAttribute("onLineCount").toString());
        count++;
        ctx.setAttribute("onLineCount",count);
        int maxOnLineCount = Integer.parseInt(ctx.getAttribute("maxOnLineCount").toString());
        if (count > maxOnLineCount){
            ctx.setAttribute("maxOnLineCount", count);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ctx.setAttribute("date",df.format(new Date()));
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext app = httpSessionEvent.getSession().getServletContext();
        int count = Integer.parseInt(app.getAttribute("onLineCount").toString());
        count--;
        app.setAttribute("onLineCount",count);

    }
}
