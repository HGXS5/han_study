<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/23
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String s = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%--静态包含--%>
<%@ include file="../error.jsp" %>

<%--动态包含--%>
<jsp:include page="../upload/index.jsp">
    <%--向被包含的页面传递参数--%>
    <jsp:param name="参数名称" value="参数值"/>
</jsp:include>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=s%>">
    <title>首页</title>
    <style type="text/css">
        * {
            font-family: "Arial";
        }
    </style>
</head>

<body>
<h1>hell.world</h1>
<hr>
<h2>Current time is :<%=new java.util.Date().toString()%>
</h2>
</body>
</html>
