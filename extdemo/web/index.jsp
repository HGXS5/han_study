<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2021/11/26
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<%--<script type="application/javascript" src="./js/han/regexDemo.js"></script>--%>
<body>
<h2>Hello</h2>
<!--enctype="multipart/form-data"支持把部分文件上传包裹图片text文档，excel等-->
<form method="post" action="fileUpload.do" name="fileupload" enctype="multipart/form-data">
    姓名： <input type="file" name="fileupload"></br>
    姓名： <input type="file" name="fileupload"></br>
    <button type="submit">提交</button>
</form>
<a href="download?fileName=工作簿1.xlsx">下载</a>
<button onclick="download()">下载附件</button>

<script>
    function download() {
        window.location.href="download?fileName=工作簿1.xlsx"
    }
</script>
</body>
</html>
