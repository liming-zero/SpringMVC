<%--
  Created by IntelliJ IDEA.
  User: liming
  Date: 2021/2/19
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/">
    <title>查询学生AJAX</title>
</head>
<body>
    <script type="text/javascript" src="js/JQuery3.5.0.js"></script>
    <script type="text/javascript">
        $(function(){
            //在当前页面load对象加载后，执行loadStudentData函数
            loadStudentData();

            $("#searchStudent").click(function(){
                loadStudentData();
            })
        })

        function loadStudentData(){
            $.ajax({
                url:"student/selectStudents.do",
                type:"get",
                dataType:"json",
                success:function (resp){
                    //清除旧的数据
                    $("#info").html("");
                    //增加新的数据
                    $.each(resp, function (index,element){
                        $("#info").append("<tr>" + "<td>" + element.id + "</td>" + "<td>" + element.name + "</td>" + "<td>" + element.age + "</td>")
                            .append("</tr>");
                    })
                }
            })
        }
    </script>

    <div align="center">
        <table>
            <thead>
                <tr>
                    <td>学号</td>
                    <td>姓名</td>
                    <td>年龄</td>
                </tr>
            </thead>
            <tbody id="info">

            </tbody>
        </table>
        <input id="searchStudent" type="submit" value="查询">
    </div>
</body>
</html>
