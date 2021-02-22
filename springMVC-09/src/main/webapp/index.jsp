<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/">
    <script type="text/javascript" src="js/JQuery3.5.0.js"></script>
</head>
<body>
    <p>全局处理异常</p>
    <form action="some.do" method="post">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
