package com.han.test3;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"*"},initParams = {@WebInitParam(name="encoding",value = "utf-8")})
public class CodingFilter implements Filter {
    private String defaultEncoding = "utf-8";
    public void init(FilterConfig filterConfig) throws ServletException {
        String encoding = filterConfig.getInitParameter("encoding");
        if (encoding != null){
            defaultEncoding = encoding;
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(defaultEncoding);
        servletResponse.setCharacterEncoding(defaultEncoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
