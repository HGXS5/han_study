package cn.han.filter;

import javax.servlet.*;
import java.io.IOException;

public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest.getRemoteHost()+servletRequest.getRemoteAddr());
    }

    @Override
    public void destroy() {

    }
}
