package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value = "/user/first.do")
    public ModelAndView doSome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","项目的地址问题");
        mv.addObject("fun","+'/'与不加'/'的区别");
        mv.setViewName("show");
        return mv;
    }
}