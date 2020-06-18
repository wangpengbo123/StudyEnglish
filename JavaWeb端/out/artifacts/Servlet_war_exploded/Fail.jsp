<%--
  Created by IntelliJ IDEA.
  User: 王鹏搏
  Date: 2020/4/9
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆失败</title>
    <style>
        h2{

        }
        p{
            text-align: center;
        }
        span{
            color: red;
        }
    </style>
</head>
<body>
    <h3>登陆失败，账号或密码错误，请重新登录</h3>
    <p>
        <span id="time">5</span>秒后自动跳转到登录页面
    </p>
    <script>
        var time = document.getElementById("time");
        var t = 5;
        function auto() {
            if (t==0){
                location.href="index.jsp";
            }
            t--;
            time.innerHTML=t+"";
        }
        setInterval(auto,1000);
    </script>
</body>
</html>
