<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/3
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page  pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>Photo Upload</title>
</head>
<body>
<h1>Select your photo and upload</h1>
<hr/>
<div style="color: red;font-size: 14px;">${hint}</div>
<form action="UploadServlet" method="post" enctype="multipart/form-data">
    Pho file:
    <input type="file" name="photo"/>
    <input type="submit" value="Upload"/>
</form>
</body>
</html>
