<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/">
    <title>Title</title>
    <script type="text/javascript" src="js/JQuery3.5.0.js"></script>
</head>
<body>
    <form action="some.do" method="post">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>
    <br>
    <img src="images/login1.jpg" alt="我是一个静态资源，不能显示">
</body>
</html>
