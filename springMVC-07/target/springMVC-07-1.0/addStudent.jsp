<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/">
    <title>注册学生</title>
</head>
<body>
    <div align="center">
        <form action="student/addStudent.do" method="post">
            <table>
                <tr>
                    <td>学生姓名:<input type="text" name="name"></td>
                </tr>
                <tr>
                    <td>学生年龄:<input type="text" name="age"></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;</td>
                    <td><input type="submit" value="提交"></td>
                </tr>

            </table>
        </form>
    </div>
</body>
</html>
