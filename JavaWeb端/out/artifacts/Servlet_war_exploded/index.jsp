<%--
  Created by IntelliJ IDEA.
  User: 王鹏搏
  Date: 2020/3/31
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>注册页面</title>
  <style >
    body{
      background: url("bg.jpg") ;
      background-size: cover;
    }
    .Login.layout{
      width: 500px;
      height: 300px;
      border: 3px solid #EEEEEE;
      margin-top: auto;
    }
  </style>
</head>
<body>
<h2 style="color: aqua; margin-left: 50px;margin-top: 50px">四六级小助手后台管理系统</h2>
<div class="Login.layout" style="margin-top: 350px; margin-left: 100px" >
  <form action="/login/loginServlet" method="post">
    <table>
      <tr>
        <td style="color: bisque;padding: 10px;border-radius: 5px">
          <label for="username" >用户名：</label>
        </td>
        <td>
          <input type="text" name="username" placeholder="请输入用户名" id="username" style="border-radius: 3px"><br>
        </td>
      <tr>
        <td style="color: bisque;padding: 10px;border-radius: 5px">
          <label for="password">密&nbsp;&nbsp;&nbsp;码：</label>
        </td>
        <td><input type="password" name="password" placeholder="请输入密码" id="password" style="border-radius: 3px"><br></td>
      </tr>
      <tr>
        <td align="center" colspan="2" style="padding: 10px">
          <input type="submit" value="登录" style="border-radius: 3px">
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>