package com.han.test1;

//import org.apache.jasper.runtime.HttpJspBase;
//import org.apache.jasper.runtime.InstanceManagerFactory;
//import org.apache.jasper.runtime.JspSourceDependent;
//import org.apache.tomcat.InstanceManager;
//import sun.applet.resources.MsgAppletViewer;

import javax.el.ExpressionFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.Map;

//public final class Index_Jsp extends HttpJspBase implements JspSourceDependent {
//    private static final JspFactory _jspFactory = JspFactory.getDefaultFactory();
//    private static Map<String, Long> _jsp_Dependants;
//    private ExpressionFactory _el_expressionFactory;
//    private InstanceManager _jsp_instanceManager;
//
//    public Map<String, Long> getDependants() {
//        return _jsp_Dependants;
//    }
//    @Override
//    public void _jspInit() {
//        _el_expressionFactory = _jspFactory.getJspApplicationContext(
//                getServletConfig().getServletContext()).getExpressionFactory();
//        _jsp_instanceManager = InstanceManagerFactory
//                .getInstanceManager(getServletConfig());
//    }
//    @Override
//    protected void _jspDestroy() {
//    }
//    public void _jspService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //内置对象就在这里定义
//        final PageContext pageContext;
//        HttpSession session = null;
//        final ServletContext application;
//        final ServletConfig config;
//        JspWriter out = null;
//        final Object page = this;
//        JspWriter _jsp_out = null;
//        PageContext _jsp_page_context = null;
//
//        try {
//            response.setContentType("text/html;charset=UTF-8");
//            pageContext = _jspFactory.getPageContext(this, request, response,
//                    null, true, 8192, true);
//            _jsp_page_context = pageContext;
//
//            application = pageContext.getServletContext();
//            config = pageContext.getServletConfig();
//            session = pageContext.getSession();
//            out = pageContext.getOut();
//
//            _jsp_out = out;
//            out.write('\r');
//            out.write('\n');
//
//            String path = request.getContextPath();
//            String basePath = request.getScheme() + "://"
//                    + request.getServerName() + ":" + request.getServerPort()
//                    + path + "/";
//            //以下代码通过输出流将HTML标签输出到浏览器中
//            out.write("\r\n");
//            out.write("\r\n");
//            out.write("<!DOCTYPE html>\r\n");
//            out.write("<html>\r\n");
//            out.write("  <head>\r\n");
//            out.write("    <base href=\"");
//            out.print(basePath);
//            out.write("\">\r\n");
//            out.write("    <title>首页</title>\r\n");
//            out.write("    <style type=\"text/css\">\r\n");
//            out.write("    \t* { font-family: \"Arial\"; }\r\n");
//            out.write("    </style>\r\n");
//            out.write("  </head>\r\n");
//            out.write("  \r\n");
//            out.write("  <body>\r\n");
//            out.write("    <h1>Hello, World!</h1>\r\n");
//            out.write("    <hr/>\r\n");
//            out.write("    <h2>Current time is: ");
//            out.print(new java.util.Date().toString());
//            out.write("</h2>\r\n");
//            out.write("  </body>\r\n");
//            out.write("</html>\r\n");
//        } catch (Throwable  t) {
//            if (!(t instanceof javax.servlet.jsp.SkipPageException)) {
//                out = _jsp_out;
//                if (out != null && out.getBufferSize() != 0)
//                    try {
//                        out.clearBuffer();
//                    } catch (java.io.IOException e) {
//                    }
//                if (_jsp_page_context != null)
//                    _jsp_page_context.handlePageException(t);
//                else
//                    throw new ServletException(t);
//            }
//        } finally {
//            _jspFactory.releasePageContext(_jsp_page_context);
//        }
//    }
//}
