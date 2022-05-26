package cn.han.cpam.controller;

import cn.han.cpam.util.UrmConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class ItumFilter implements Filter
{
	protected final static Logger logger = LoggerFactory.getLogger(ItumFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (request.getParameterMap().toString().indexOf("redirect:") > -1)
		{
			response.getWriter().println("firewall here!!!");
			return;
		}
		String uri = httpRequest.getRequestURI(); 
 
		HttpSession session = httpRequest.getSession();
		String requestType = httpRequest.getHeader("X-Requested-With");
		
		String name = (String) session.getAttribute("userName");
		if ((null == name || name.equals("")) && !uri.contains("login") && !uri.contains("sync_") && !uri.contains("/images/") && !uri.contains("/services/"))
		{ // 判断是否为ajax请求,因为ajax请求的跳转不起作用
			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest"))
			{
				httpResponse.addHeader("sessionstatus", "timeOut");
				httpResponse.addHeader("loginPath", httpRequest.getContextPath() + "/login.jsp");
				chain.doFilter(request, response);// 不可少，否则请求会出错

			} else
			{
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
				return;
			}

		}
		if (uri.endsWith("/"))
		{
			httpResponse.sendRedirect("index.jsp");
			return;
		}
		UrmConfig.setOneApp(true);
		try
		{
			chain.doFilter(request, response);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			logger.error("ItumFilter处理链出现错误");
		}
	}

	@Override
	public void destroy()
	{
	}

	private static ApplicationContext applicationContext;


	public static Object getBean(String name)
	{
		return applicationContext.getBean(name);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
	}
}
