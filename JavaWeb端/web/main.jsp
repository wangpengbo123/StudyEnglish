<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: 王鹏搏
  Date: 2020/4/1
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
    <style>
        h3{
            align-content: center;
        }
        button{
            font-size: 30px;
            background-color: aqua;
        }
    </style>
</head>
<body>

<c:if test="${requestScope.flag==false}">
    <h3>${requestScope.account}您好，欢迎您首次访问</h3>
</c:if>
<c:if test="${requestScope.flag==true}">
    <h3>欢迎${requestScope.account}回来，您上次的登陆时间是：${pageContext.request.getAttribute("decode")}</h3>
</c:if>

       <p>
            请选择您要进行的操作：
        </p>
    <hr>
        <button onclick="out1()" type="submit" value="留言管理" style="border-radius: 3px">留言管理</button>
    <br>
    <br>
    <br>
    <button onclick="out2()" type="submit" value="试题修改" style="border-radius: 3px;">试题管理</button>

    <script>
        function out1() {
            window.open("${pageContext.request.contextPath}/messageServlet");
        }
        function out2() {
            window.open("/login/testServlet");
        }
    </script>
</body>
</html>
