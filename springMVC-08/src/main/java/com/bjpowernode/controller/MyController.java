package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value = "/doForward.do")

    /**
     * 处理器方法返回ModelAndView，实现转发forward
     * 语法: setViewName("forward:视图完整路径")
     * forward特点: 不和视图解析器一同使用，就当项目中没有视图解析器
     */
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","实现转发forward");
        mv.addObject("fun","不和视图解析器一同使用，就当项目中没有视图解析器");
        //显示转发
        mv.setViewName("forward:/WEB-INF/view/show.jsp");
        return mv;
    }

    /**
     * 处理器方法返回ModelAndView，实现转发redirect
     * 语法: setViewName("redirect:视图完整路径")
     * redirect特点: 不和视图解析器一同使用，就当项目中没有视图解析器
     */
    @RequestMapping("/doRedirect.do")
    public ModelAndView doOther(String username, Integer userage) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username",username);
        mv.addObject("userage", userage);
        //重定向
        mv.setViewName("redirect:/demo.jsp");
        return mv;
    }
}