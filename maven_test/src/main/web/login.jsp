<!DOCTYPE html >
<!--<html xmlns:th="http://www.thymeleaf.org" lang="en">-->
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5" lang="en">

<head>
    <!--    <link rel = "stylesheet" th:href="@{/css/jquery.min.css}"/>-->
    <meta charset="UTF-8">
    <title>登陆</title>
</head>
<body>
<h2>标准登陆页面</h2>
<h3>表单登陆</h3>
<!--<h3>${user.name}</h3>-->
<form action="/han" method="post" name="loginName">
    <table>
        <input type="hidden" th:value="${_csrf.token}" name="_csrf" th:if="${_csrf}">
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>记住:</td>
            <td><input type="checkbox" name="remember-me" value="true"></td>
        </tr>
        <tr>
            <td>授权:</td>
            <td><input type="checkbox" id="authId"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="授权" onclick="authorization()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
                    type="reset" value="重置"/>
            </td>
        </tr>

    </table>
</form>
<script>
    window.onload=function () {
        alert("start")
        var btn = document.getElementsByName("submit")[0];
        var byId = document.getElementById("authId");
        btn.disabled = false;
        alert(byId)
        if (byId[0].type == 'checkbox' && byId[0].checked) {
            alert("授权开始")
            authorization();
        }else {
            btn.disabled = true;
        }
        alert("end")
    }
    // var chk = document.getElementsByName('authName')[0];
    // var btn = document.getElementsByName("submit")[0];
    // var fname = document.forms["loginName"]["username"].value;
    // var sname = document.forms["loginName"]["password"].value;
    // // var cname = document.forms["bookingForm"]["companyName"].value;
    //
    // document.getElementsByName('authName')[0].onclick = function() {
    //     textCol()
    // };
    //
    // function textCol() {
    //     if (chk.checked) {
    //         document.getElementById("authId").style.color = "black";
    //         document.getElementById("authId").style.fontWeight = "normal";
    //         btn.disabled = false;
    //
    //         if (fname == null || fname == "", sname == null || sname == "") {
    //             btn.disabled = true;
    //
    //         }
    //     }
    //     else {
    //         document.getElementById("authId").style.color = "red";
    //         document.getElementById("authId").style.fontWeight = "bold";
    //         btn.disabled = true;
    //
    //     }
    // }

    function authorization() {
        var myAjax = new XMLHttpRequest();
        //设置方法及请求路径
        myAjax.open("get", "http://localhost:8080/oauth/authorize?response_type=code&client_id=hanlibo&redirect_uri=http://localhost:8080/code/authorizationCode&scope=all");
        //设置请求头信息 post
        // myAjax.setRequestHeader('content-type', 'allocation/x-www-form-urlencaded');
        //发送请求
        myAjax.send();
        //获取响应结果
        myAjax.onload = function () {
            console.log(myAjax.response);
        }
    }


</script>
</body>
</html>