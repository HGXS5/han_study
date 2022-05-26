package cn.han.cpam.util;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse rsp, Object obj, Exception e)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse rsp,
			Object obj, ModelAndView mode) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp,
			Object obj) throws Exception {
		HttpSession session = req.getSession();
		if(null!=session.getAttribute("user")){
			return true;
		}else{
			String path = req.getContextPath();
			String loginUrl = req.getScheme() + "://"+ req.getServerName() + ":" + req.getServerPort() + path + "/login.jsp";
			//rsp.sendRedirect("https://www.baidu.com/");
			//req.getRequestDispatcher("/login1").forward(req,rsp);
			//rsp.sendRedirect("/login1");
			//rsp.addHeader("sessionstatus", "timeOut");
			//rsp.addHeader("loginPath", basePath);
			session.setAttribute("sessionstate", "timeOut");
			session.setAttribute("loginUrl", loginUrl);
			return false;
		}
	}

}
