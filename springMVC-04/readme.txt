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
