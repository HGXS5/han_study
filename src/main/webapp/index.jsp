
<%@ page contentType="text/html;charset=UTF-8" language="java" isThreadSafe="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/23
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%--
    isThreadSafe:表示线程的安全
--%>
<%@ taglib prefix="my" uri="/WEB-INF/my.tld" %>
<%
    String path = request.getContextPath();
    String s = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=s%>">
    <title>首页</title>
    <style type="text/css">
        *{
            font-family: "Arial"; font-size:72px;
         }
    </style>
</head>

<body>
<my:time format="yyyy-MM-dd" backColor="blue" foreColor="yellow"/>
<h1>hell.world</h1>
<hr>
<h2>Current time is :<%=new java.util.Date().toString()%></h2>
</body>
</html>
