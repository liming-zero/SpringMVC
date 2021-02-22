<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3>/WEB-INF/view/show.jsp从request作用域获取数据</h3><br>
    <h3>用户姓名:${param.username}</h3>
    <h3>用户年龄:${param.userage}</h3>
    <h3>相当于在url地址取参数数据:<%=request.getParameter("username")%></h3>
</body>
</html>
