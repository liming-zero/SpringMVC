<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>提交用户参数给Controller</p>
    <form action="receivproperty.do" method="post">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>
    <br>
    <p>请求中参数名和方法的形参名不一样，使用对象接收</p>
    <form action="receivparam.do" method="post">
        姓名:<input type="text" name="rusername"><br>
        年龄:<input type="text" name="ruserage"><br>
        <input type="submit" value="提交">
    </form>

    <p>使用java对象接收请求参数</p>
    <form action="receivobject.do" method="post">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
