<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/">
    <title>功能入口</title>
</head>
<body>
    <div align="center">
    <p>SSM整合的例子</p>
        <img src="images/welcome.gif">
        <table>
            <tr>
                <td><a href="addStudent.jsp">注册学生</a></td>
            </tr>
            <tr>
                <td><a href="selectStudents.jsp">浏览学生</a></td>
            </tr>
        </table>
    </div>
</body>
</html>
