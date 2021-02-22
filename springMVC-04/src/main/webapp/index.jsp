<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/JQuery3.5.0.js"></script>
    <script type="text/javascript">
        $(function (){
            $("button").click(function(){
                $.ajax({
                    //url:"returnVoid-ajax.do",
                    url:"returnStringData.do",
                    data:{
                        username:"zhangsan",
                        userage:20
                    },
                    type:"post",
                    //dateType:"json",
                    success:function (data){
                        alert(data);
                        //data从服务器端返回的是json格式的字符串("username":"zhangwsan","userage":23)
                        //jQuery会把字符串传为json对象，赋值给data形参

                        //[{"username":"李明","userage":23},{"username":"张三","userage":24}]
                        //Controller的处理器方法返回的是List<User>,展示的是数组，需要遍历数组
                        //alert(data.username+ " " +data.userage);
                        /*$.each(data,function (index,element){
                            alert(element.username + " " + element.userage)
                        })*/
                    }
                })
            })
        })
    </script>
</head>
<body>
    <p>处理器方法返回String表示视图解析器名称</p>
    <form action="returnString-view.do" method="post">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>
    <br>
    <p>处理器方法返回String表示视图完整路径名称</p>
    <form action="returnString-view2.do" method="post">
        姓名:<input type="text" name="username"><br>
        年龄:<input type="text" name="userage"><br>
        <input type="submit" value="提交">
    </form>

    <br>
    <button id="btn">发起AJAX请求</button>
</body>
</html>
