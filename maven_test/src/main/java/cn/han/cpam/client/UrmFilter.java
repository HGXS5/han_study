package cn.han.cpam.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UrmFilter implements Filter {	
	protected final static Logger logger = LoggerFactory.getLogger(cn.han.cpam.client.UrmFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = ((HttpServletRequest)request);
		String url = httpRequest.getRequestURI();
		HttpSession session = httpRequest.getSession();
		UrmClient urmClient = UrmClient.get(session);
		if(urmClient != null && urmClient.isUnAuth(url)){
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<span style='font-size:14px;color:red'>[" + url + "]资源未授权!</span>");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig config) throws ServletException {}
}