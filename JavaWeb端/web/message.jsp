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
    <title>留言管理</title>
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
        td{

            background-color: aquamarine;
            text-align: center;
        }
        th{
            background-color: aqua;
            text-align: center;
        }
    </style>
    <%

    %>
</head>
<body>

    <table>

        <caption>留言内容表</caption>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>内容</th>
            <th>备注</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach var="msg" items="${message}" varStatus="s" >
            <tr>
                    <td>${s.count}</td>
                    <td>${msg.name}</td>
                    <td>${msg.content}</td>
                    <td>${msg.remark}</td>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    <a href="javascript:deleteMsg(${msg.id})"> 删除</a>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/findMsgServlet?id=${msg.id}">回复</a>
                                </td>
                            </tr>
                        </table>
                    </td>
            </tr>
        </c:forEach>

    </table>
    <script>
        function deleteMsg(id) {
            if (confirm("您真的要删除吗？")){
                location.href = "${pageContext.request.contextPath}/deleteMsgServlet?id="+ id;
            }
        }
    </script>
   <!--<script>
        //获取按钮
        var btn_add = document.getElementById("btn_add");
        //获取文本框中的内容
        btn_add.onclick = function () {
            var id = document.getElementById("id").value;
            var question = document.getElementById("question").value;
            var answer = document.getElementById("answer").value;
            var optionA = document.getElementById("optionA").value;
            var optionB = document.getElementById("optionB").value;
            var optionC = document.getElementById("optionC").value;
            var optionD = document.getElementById("optionD").value;

            //创建td，赋值td的标签体
            var td_id = document.createElement("td");
            var text_id = document.createTextNode(id);
            td_id.appendChild(text_id);
            var td_question = document.createElement("td");
            var text_question = document.createTextNode(question);
            td_question.appendChild(text_question);
            var td_answer = document.createElement("td");
            var text_answer = document.createTextNode(answer);
            td_answer.appendChild(text_answer);
            var td_optionA = document.createElement("td");
            var text_optionA = document.createTextNode(optionA);
            td_optionA.appendChild(text_optionA);
            var td_optionB = document.createElement("td");
            var text_optionB = document.createTextNode(optionB);
            td_optionB.appendChild(text_optionB);
            var td_optionC = document.createElement("td");
            var text_optionC = document.createTextNode(optionC);
            td_optionC.appendChild(text_optionC);
            var td_optionD = document.createElement("td");
            var text_optionD = document.createTextNode(optionD);
            td_optionD.appendChild(text_optionD);
            var td_a = document.createElement("td");
            var ele_a = document.createElement("a");
            ele_a.setAttribute("href","javascript:void(0)");
            ele_a.setAttribute("onclick","deltr(this)");
            var text = document.createTextNode("删除");
            ele_a.appendChild(text);
            td_a.appendChild(ele_a)
            //创建tr
            var tr = document.createElement("tr");
            tr.appendChild(td_id);
            tr.appendChild(td_question);
            tr.appendChild(td_answer);
            tr.appendChild(td_optionA);
            tr.appendChild(td_optionB);
            tr.appendChild(td_optionC);
            tr.appendChild(td_optionD);
            tr.appendChild(td_a);
            //将tr添加到table中
            var table = document.getElementsByTagName("table")[0];
            table.appendChild(tr);
            //使用innerHtml简化代码
           /* table.innerHTML +="<tr>\n" +
                "<td>"+id+"</td>\n"+
                "<td>"+question+"</td>\n"+
                "<td>"+answer+"</td>\n"+
                "<td>"+optionA+"</td>\n"+
                "<td>"+optionB+"</td>\n"+
                "<td>"+optionC+"</td>\n"+
                "<td>"+optionD+"</td>\n"+
                "<td><a href='javascript:void(0);' onclick='deltr(this)'></a></td>"+
                "</tr>"*/

        }
        function deltr(obj) {
            var table = obj.parentNode.parentNode.parentNode;
            var tr = obj.parentNode.parentNode;
            table.removeChild(tr);
        }
    </script>-->
</body>
</html>
