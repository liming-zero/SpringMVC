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

springMVC-04
    处理方法的返回值表示请求的处理结果
        1.ModelAndView:有数据和视图，对视图进行forward
        2.String:表示视图，可以是逻辑名称，也可以是完整视图路径
        3.void:不能表示数据，也不能表示视图。在处理AJAX的时候，可以使用void返回值。
          通过HttpServletResponse输出数据，响应AJAX请求。AJAX请求服务器端返回的就是数据，和视图无关。
        4.Object: 例如String,Integer,Map,List,Student等都是对象，对象有属性，属性就是数据。所以返回Object表示数据，和视图无关。
          可以使用对象表示的数据，响应ajax请求。

          现在做AJAX，主要使用JSON的数据格式。实现步骤:
          1.加入处理json的工具库的依赖，springmvc默认使用的jackson
          2.在springmvc配置文件之间加入 <mvc:annotation-driven> 注解驱动。
              json = mapper.writeValueAsString(user);
          3.在处理器方法的上面加入@ResponseBody注解
              response.setContentType("application/json;charset=utf-8");
              PrintWriter out = response.getWriter();
              out.println(json);

        springmvc处理器方法返回Object，可以转为json输出到浏览器，响应ajax的内部原理:
            1.<mvc:annotation-driven> 注解驱动。
              注解驱动实现的功能是 完成java对象到json，xml，text，二进制等数据格式的转换。
              <mvc:annotation-driven>在加入到springmvc配置文件后，会自动创建HttpMessageConverter接口的7个实现类对象。
              其中包括MappingJackson2HttpMessageConverter(使用jackson工具库中的ObjectMapper实现java对象转为json字符串的)

              HttpMessageConverter接口:消息转换器。功能:定义了java对象转为json，xml等数据格式的方法。
              这个接口有很多实现类，这些实现类完成了 java到json，xml，二进制数据的转换。

              下面的两个方法是控制器类把结果输出给浏览器时使用的:
              boolean canWrite(Class<?> var1, @Nullable MediaType var2);
              void write(T var1, @Nullable MediaType var2, HttpOutputMessage var3);
                1) canWrite：作用检查处理器方法的返回值，能不能转换为var2表示的数据格式，如果检查能转换为JSON,canWrite返回true
                   MediaType表示数据格式的，例如json，xml等。
                2) write：把处理器方法的返回值对象，调用jackson中的ObjectMapper转为json字符串。
                   json = mapper.writeValueAsString(user);

            2.@ResponseBody注解
              放在处理器方法的上面，通过HttpServletResponse输出数据，响应ajax请求的。
                      PrintWriter out = response.getWriter();
                      out.println(json);
                      out.flush();
                      out.close();

springMVC-05
    关于DispatcherServlet(中央调度器)的url-pattern设置为"/"
    url-pattern使用斜杠"/"
        当项目中使用 / ，他会替代Tomcat中的defaultServlet
        导致所有的静态资源都给DispatcherServlet处理，默认情况下DispatcherServlet没有处理静态资源的能力。
        没有控制器对象能处理静态资源的访问。所以静态资源都是404不能处理。
    第一种处理静态资源的方式:
        需要在springmvc配置文件加入 <mvc:default-servlet-handler>
        原理是: 加入这个标签后，框架会自动创建控制器对象DefaultServletHttpRequestHandler(类似我们自己创建的MyController)
        DefaultServletHttpRequestHandler这个对象可以把接收的请求转发给 Tomcat的DefaultServlet
        <mvc:default-servlet-handler> 和@RequestMapping注解有冲突，需要加入注解驱动<mvc:annotation-driven /> 解决问题
    第二种处理静态资源的方式:(常用)
        加入标签<mvc:resources mapping="" location="" />
        mvc:resources 加入后框架会创建 ResourceHttpRequestHandler这个处理器对象
        让这个对象处理静态资源的访问，不依赖Tomcat服务器，主要使用的方式
        mapping: 访问静态资源的uri地址，使用通配符 **
        location: 静态资源在你的项目中的目录位置。
        <!--mvc:resources和RequestMapping有一定的冲突-->

springMVC-06
    解决jsp页面中的路径问题，用"/"还是不用
        在jsp，html使用的地址，都是在前端页面中的地址，都是相对地址
    1.绝对地址:带有协议名称的是绝对地址。http://www.baidu.com
    2.相对地址:没有协议开头的，例如 user/some.do 相对地址不能独立使用，必须有一个参考地址
    常用的使用方式:
        1.加"/",在/前使用EL表达式${pageContext.request.contextPath}
        2.不加"/",加入<base>标签
        <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${pageContext.request.contextPath}/">

springMVC-07
    SSM框架整合开发:
        SpringMVC:视图层，界面层，负责接收请求，显示处理结果的。
        Spring:业务层，管理service,dao,工具类对象的。
        Mybatis:持久层，访问数据库的
    用户发起请求-->SpringMVC接收-->Spring中的service对象处理业务-->Mybatis处理数据
        1.第一个SpringMVC容器，管理Controller控制器对象的。
        2.第二个容器Spring容器，管理service,dao,工具类对象。
    我们要做的是把使用的对象交给合适的容器创建，管理。
        1.把Controller还有web开发的相关对象交给SpringMVC容器。这些web用的对象写在SpringMVC配置文件中。
        2.service,dao对象定义在spring的配置文件中，让spring管理这些对象。
    SpringMVC和Spring容器是有关系的，关系已经确定好了。SpringMVC容器是Spring容器的子容器，类似java中的继承。
    在子容器中的Controller可以访问父容器中的service对象，就可以实现Controller使用service对象。

    实现步骤:
        0.使用springdb的mysql库，表使用t_student(id,name,age)
        1.新建maven项目
        2.加入依赖:
            * springmvc,spring,mybatis,jackson,mysql,druid连接池,jsp,servlet
        3.配置web.xml文件
            * 注册DispatcherServlet
                目的:
                    1.创建springmvc容器对象，才能创建controller对象
                    2.创建的是Servlet,才能接收用户的请求
            * 注册spring的监听器:ContextLoaderListener
                目的:
                    1.创建spring的容器对象，才能创建service,dao等对象
            * 注册字符集过滤器，解决post请求乱码问题
        4.创建包: controller, service, dao, entity
        5.写springmvc, spring, mybatis 的配置文件
            * springmvc配置文件
            * spring配置文件
            * mybatis主配置文件
            * 数据库的属性配置文件
        6.写代码, dao接口和mapper文件， service和实现类， controller, 实体类。
        7.写jsp页面

springMVC-08

