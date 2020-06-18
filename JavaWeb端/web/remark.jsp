<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 王鹏搏
  Date: 2020/4/9
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> 留言处理</title>

</head>
<body>


        <form action="${pageContext.request.contextPath}/remarkServlet" method="post">
            <!--隐藏域-->
            <input type="hidden" name="id" value="${msg.id}">
            <table>

                <tr>
                    <td><input type="text" id="name" name="name" value="${msg.name}"><br></td>
                </tr>
                <tr>
                    <td><input type="text" id="content" name="content" value="${msg.content}"><br></td>
                </tr>
                <tr>
                    <td><input type="text" id="remark" name="remark" value="${msg.remark}"><br></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="提交"><br>
                    </td>
                </tr>
            </table>
        </form>


</body>
</html>
