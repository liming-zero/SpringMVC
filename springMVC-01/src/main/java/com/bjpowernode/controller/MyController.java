package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//Controller:创建处理器对象的，对象放在springmvc容器中
@Controller
public class MyController {

    //处理用户提交的请求，springmvc是使用方法来处理的。
    //方法是自定义的，可以有多种返回值，多种参数，方法名称自定义

    /**
     * 准备使用doSome()方法处理some.do的请求。
     *
     * @RequestMapping:请求映射，作用是把一个请求地址和一个方法绑定在一起。一个请求指定一个方法处理。
     *          属性: 1.value 是一个String,表示请求的uri地址(some.do)。value的值必须是唯一的，不能重复。推荐地址以"/"开头
     *          位置: 1.在方法的上面，常用。    2.在类的上面
     *          说明: 使用@RequestMapping修饰的方法叫做处理器方法或者控制器方法。
     *               它修饰的方法可以处理请求的，类似Servlet中的doGet()和doPost()方法。
     *
     *          返回值: ModelAndView   Model:数据，请求处理完成后显示给用户的数据
     *                                View: 视图，比如jsp等等
     */
    @RequestMapping(value ="/some.do")
    public ModelAndView doSome(){   //doGet()
        //返回值是ModelAndView，给这个对象添加数据
        ModelAndView mv = new ModelAndView();

        //andObject(),key是String,value是Object
        //框架在请求的最后把数据存入到了request范围  request.setAttribute(key,value)
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是doSome()方法");

        //指定视图，视图的完整路径  框架对视图执行的是forward转发操作
//        mv.setViewName("/WEB-INF/view/show.jsp");
//        mv.setViewName("/WEB-INF/view/show.jsp");

        //当配置了视图解析器后，可以使用逻辑名称(文件名)，指定视图
        //框架会使用视图解析器的前缀 + 逻辑名称 + 后缀 组成完整路径，这里就是字符串连接操作
        mv.setViewName("show");

        return mv;
    }

    @RequestMapping(value = {"/other.do","/last.do"})
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是doOther()方法");
        mv.setViewName("other");

        return mv;
    }
}
