package com.bjpowernode.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//拦截器类: 拦截用户的请求
public class MyInterceptor implements HandlerInterceptor {

    //验证用户的登录信息，正确return true, 其他情况return false
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器的preHandle方法执行了");
        //从session中获取name的值
        String loginName = "";
        Object username = request.getSession().getAttribute("username");
        if (username != null) {
            loginName = (String)username;
        }

        //判断登录的账号，是否符合要求，不符合返回false
        if (!"zhangsan".equals(loginName)){
            request.getRequestDispatcher("demo.jsp").forward(request,response);
            return false;
        }

        return true;
    }

}
