springMVC-01
    需求: 用户在页面发起一个请求，请求交给springMVC的控制器对象，并显示请求的处理结果(在结果页面显示一个欢迎语句)。
    步骤: 1.新建web maven工程
         2.加入依赖
            1) spring-webmvc依赖，他会间接把spring的依赖都加入到项目中。
            2) servlet依赖和jsp依赖
         3.重点:在web.xml中注册springMVC框架的核心对象DispatcherServlet
            1) DispatcherServlet叫做中央调度器，是一个servlet,它的父类继承HttpServlet
            2) DispatcherServlet也叫做前端控制器( front com.bjpowernode.controller )
            3) DispatcherServlet负责接收用户提交的请求，调用其他的控制器对象，并把请求的处理结果显示给用户。
         4.创建一个发起请求的页面 index.jsp
         5.创建控制器(处理器)类
            1) 在类的上面加入@Controller注解，创建对象，并放入到springMVC容器中
            2) 在类中的方法上面加入@RequestMapping注解。
         6.创建一个作为结果的jsp,显示请求的处理结果。
         7.创建springmvc的配置文件(和spring的配置文件一样)
            1) 声明组件扫描器，指定@Controller注解所在的包名
            2) 声明视图解析器，帮助处理视图的。
    springmvc请求的处理流程:
         1.发起some.do
         2.Tomcat(web.xml--url--pattern知道 *.do的请求给DispatcherServlet)
         3.DispatcherServlet(根据springmvc.xml配置知道 some.do --> doSome()方法)
         4.DispatcherServlet把some.do转发给MyController.doSome()方法
         5.框架执行doSome()方法把得到的ModelAndView对象进行处理，转发给show.jsp

springMVC-02
    @RequestMapping的使用

springMVC-03
    接收用户请求的参数:使用的是处理器方法的形参
        1.HttpServletRequest
        2.HttpServletResponse
        3.HttpServletSession
        4.用户提交的数据
    接收用户提交的参数:
        1.逐个接收
        2.对象接收
    注意:在提交请求参数时，get请求方式中文没有乱码，而post请求有乱码问题，需要使用过滤器解决乱码问题。
        过滤器可以自定义，也可以使用框架中自定义的过滤器，CharacterEncodingFilter