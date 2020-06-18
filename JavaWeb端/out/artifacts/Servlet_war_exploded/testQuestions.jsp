<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.huaqing.dao.testDao" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.huaqing.Bean.testQuestions" %><%--
  Created by IntelliJ IDEA.
  User: 王鹏搏
  Date: 2020/4/9
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>试题管理</title>
    <style>
        table{
            border: 1px solid;
            margin: auto;
            width: auto;
        }
        div{
            text-align: center;
            margin: auto;
        }
        th{
            margin: 20px;

        }
    </style>
    <script>
    function deletetest(id) {
        //删除安全提示
        if (confirm("您确定要删除吗？")){
            location.href = "${pageContext.request.contextPath}/deleteTestServlet?id="+id;
        }
    }
    // <!--获取页面的组件，需要页面加载完成后-->
    window.onload = function () {
        //给删除选中按钮天界单击事件
        var btn_1 = document.getElementById("deleteselected");
        btn_1.onclick = function () {
            if (confirm(""))
            //表单提交
                document.getElementById("form").submit();
        }
        <!--获取第一个id-->
        var id = document.getElementById("fristCb");
        id.onclick = function () {
            //获取下边列表中的所有的cb
            var cbs = document.getElementsByName("uid");
            //遍历cbs
            for (var i = 0; i <cbs.length;i++){
                //设置这些cbs的状态等于fristcb
                cbs[i].checked = this.checked;
            }
        }
    }
</script>
</head>
<body>



    <div>
        <form action="${pageContext.request.contextPath}/addtestServlet" method="post">
        <input type="text" id="question" name="question" placeholder="请输入题目">
        <input type="text" id="answer" name="answer" placeholder="请输入试题答案">
        <input type="text" id="optionA" name="optionA" placeholder="请输入选项A">
        <input type="text" id="optionB" name="optionB" placeholder="请输入选项B">
        <input type="text" id="optionC" name="optionC" placeholder="请输入选项C">
        <input type="text" id="optionD" name="optionD" placeholder="请输入选项D">
        <input type="submit"value="添加">
        </form>
    </div>
    <br>
    <div style="float: right;margin: 5px">
        <a type="button" href="javascript:void(0);" id="deleteselected">删除选中</a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/deleteSelectedServlet" method="post" >
    <table>

        <caption>试题内容表</caption>
        <tr style="background-color: aqua">
            <th><input type="checkbox" id="fristCb"></th>
            <th>编号</th>
            <th>问题</th>
            <th>答案</th>
            <th>选项A</th>
            <th>选项B</th>
            <th>选项C</th>
            <th>选项D</th>
            <th>操作</th>
        </tr>
        <c:forEach var="a" items="${requestScope.test}" varStatus="s" >
                <tr style="background-color: aquamarine">
                    <td><input type="checkbox" name="uid" value="${a.id}"></td>
                    <td>${s.count}</td>
                    <td>${a.question}</td>
                    <td>${a.answer}</td>
                    <td>${a.optionA}</td>
                    <td>${a.optionB}</td>
                    <td>${a.optionC}</td>
                    <td>${a.optionD}</td>
                    <td><a href="javascript:deletetest(${a.id})" >删除</a></td>
                </tr>
        </c:forEach>

    </table>
    </form>

</body>
</html>
