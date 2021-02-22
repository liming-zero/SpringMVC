package com.bjpowernode.controller;

import com.bjpowernode.vo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

    /**
     * 处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
     */
    @RequestMapping(value = "/returnString-view.do")
    public String doReturnView(HttpServletRequest request, String username, Integer userage) {
        //可以手动添加数据到request作用域
        request.setAttribute("myName", username);
        request.setAttribute("myAge", userage);
        //show: 逻辑视图名称，项目中配置了视图解析器
        //框架对视图执行forward转发操作
        return "show";
    }

    /**
     * 处理器方法返回String,表示视图完整路径，此时不能配置视图解析器
     */
    @RequestMapping(value = "/returnString-view2.do")
    public String doReturnView2(HttpServletRequest request, String username, Integer userage) {
        //可以手动添加数据到request作用域
        request.setAttribute("myName", username);
        request.setAttribute("myAge", userage);
        //完整视图路径，项目中不能配置视图解析器
        //框架对视图执行forward转发操作
        return "/WEB-INF/view/show.jsp";
    }

    //处理器方法返回void，响应AJAX请求
    //手工实现AJAX，json数据:代码有重复的    1.java对象转为json;  2.通过HttpServletResponse输出json数据
    @RequestMapping(value = "/returnVoid-ajax.do")
    public void doReturnVoidAjax(HttpServletResponse response, String username, Integer userage) throws Exception {
        //处理AJAX,使用JSON做数据的格式
        //service调用完成了，使用User表示处理结果
        User user = new User();
        user.setUsername(username);
        user.setUserage(userage);

        //把结果的对象转为JSON格式的数据
        String json = "{}";
        if (user != null) {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(user);
            System.out.println("User类转换的json===" + json);
        }

        //输出数据，响应AJAX的请求
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();

    }

    /**
     * 处理器方法返回一个User，通过框架转为json，响应ajax请求
     * @ResponseBody: 作用，把处理器方法返回对象转为json后，通过HttpServletResponse输出给浏览器。
     * 返回对象框架的处理流程:
     *      1.框架会把返回User类型，调用框架中的ArrayList<HttpMessageConverter>中每个类的canWrite()方法
     *        检查哪个HttpMessageConverter接口的实现类能处理User类型的数据。
     *      2.框架会调用实现类的Write(), MappingJackson2HttpMessageConverter的write()方法
     *        把李明同学的User对象转为json，调用jackson的ObjectMapper实现转为json
     *      3.框架会调用@ResponseBody把2的结果输出到浏览器，AJAX请求处理完成。
     */
    @RequestMapping(value = "/returnUserJson.do")
    @ResponseBody
    /*
    @ResponseBody注解
    放在处理器方法的上面，通过HttpServletResponse输出数据，响应ajax请求的。
    PrintWriter out = response.getWriter();
                      out.println(json);
                      out.flush();
                      out.close();              */
    public User doUserJsonObject(HttpServletResponse response, String username, Integer userage) {
        //调用service，获取请求结果数据， User对象表示结果数据
        User user = new User();
        user.setUsername("李明");
        user.setUserage(23);
        return user;    //会被框架转为json
    }

    /**
     * 处理器方法返回List<User>
     * 处理流程和Object类型是一样的
     */
    @RequestMapping(value = "/returnUserJsonArray.do")
    @ResponseBody
    public List<User> doUserJsonObjectArray(HttpServletResponse response, String username, Integer userage) {
        //调用service，获取请求结果数据， User对象表示结果数据
        List<User> userLists = new ArrayList<>();
        User user = new User();
        user.setUsername("李明");
        user.setUserage(23);
        userLists.add(user);

        user = new User();
        user.setUserage(24);
        user.setUsername("张三");
        userLists.add(user);

        return userLists;    //会被框架转为json
    }

    /**
     * 处理器方法返回的是String, String表示数据的，不是视图。
     * 区分返回值String是数据，还是视图，看有没有@ResponseBody注解
     * 如果有@ResponseBody注解，返回String就是数据，反之就是视图。
     * 处理流程类似返回对象
     *
     * 默认使用"text/plain;charset=ISO-8859-1" 作为contentType,导致中文有乱码，
     * 解决方案:给RequestMapping增加一个属性 produces,使用这个属性指定新的contentType
     *
     * 框架会调用实现类的write(),StringHttpMessageConverter的write()方法，
     * 把字符按照指定的编码处理。
     */
    @RequestMapping(value = "/returnStringData.do" ,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doStringDate(String username,Integer userage){
        return "Hello SpringMVC, 返回值String表示数据";
    }
}