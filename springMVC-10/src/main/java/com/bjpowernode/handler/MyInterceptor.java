package com.bjpowernode.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器类: 拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {

    private long beginTime = 0;
    private long endTime = 0;
    /*
    * preHandle叫做预处理方法。(重要: 是整个项目的入口，门户 )
    * 参数:
    *     Object handler : 被拦截的控制器对象
    * 返回值boolean
    *     true: 表示请求通过了拦截器的验证，可以执行预处理方法。
    *     false: 表示请求没有通过拦截器的验证，请求到达拦截器停止了
    *
    * 特点;
    *     1.方法在在控制器方法( myController的doSome )之前先执行,用户的请求首先到达此方法。
    *     2.在这个方法中可以获取请求的信息，验证请求是否符合要求。
    *       可以验证用户是否登录，验证用户是否有权限访问某个链接地址(url)
    *       如果验证失败，可以截断请求，这个请求不能被处理。如果验证成功，可以放行请求，此时控制器方法才能执行。
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器(Interceptor)的preHandle方法");
        beginTime = System.currentTimeMillis();
        //给浏览器一个返回结果
        //request.getRequestDispatcher("/demo.jsp").forward(request,response);
        return true;
    }

    /*
    * postHandle: 后处理方法
    * 参数:
    *     Object handler: 被拦截的处理器对象MyController
    *     ModelAndView modelAndView : 处理器方法的返回值
    * 特点:
    *     1.在处理器方法之后执行的( MyController.doSome() )
    *     2.能够获取到处理器方法的返回值ModelAndView, 可以修改ModelAndView中的数据和视图，可以影响到最后的执行结果。
    *     3.主要是对原来的执行结果做二次修正.
    * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器(Interceptor)的postHandle方法");
        //对MyController中的doSome方法执行结果进行调整。
        if (modelAndView != null){
            modelAndView.addObject("myDate",new Date());
            modelAndView.setViewName("other");
        }
    }

    /*
    * afterCompletion: 最后执行的方法
    * 参数:
    *     Object handler: 被拦截的处理器对象
    *     Exception ex: 程序中发生的异常
    * 特点:
    *     1.在请求处理完成后执行。框架中规定，当你的视图处理完成后，对视图执行了forward。就认为请求处理完成。
    *     2.一般是做资源回收工作的，程序请求过程中创建了一些对象，在这里可以删除，把占用的内存回收。
    * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器(Interceptor)的afterCompletion方法");
        endTime = System.currentTimeMillis();
        System.out.println("计算preHandle到请求处理完成的时间 = " + (endTime - beginTime));
    }
}
