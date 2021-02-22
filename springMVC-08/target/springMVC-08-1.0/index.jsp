<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/">
    <script type="text/javascript" src="js/JQuery3.5.0.js"></script>
</head>
<body>
    <p>当处理器方法返回ModelAndView实现forward转发操作</p>
    <form action="doForward.do">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>
    <br>

    <p>当处理器方法返回ModelAndView实现redirect重定向操作</p>
    <form action="doRedirect.do">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>

</body>
</html>
