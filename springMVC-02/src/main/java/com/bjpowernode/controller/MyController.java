package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping放在类的上面，value表示所有请求的公共部分，叫做模块名称
@RequestMapping(value = "/user")
public class MyController {

    //指定some.do使用get请求方式
    @RequestMapping(value ="/some.do", method = RequestMethod.GET)
    public ModelAndView doSome(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是doSome()方法");
        mv.setViewName("show");
        return mv;
    }

    //指定other.do使用post请求方式
    @RequestMapping(value = {"/other.do","/second.do"}, method = RequestMethod.POST)
    public ModelAndView doOther(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是doOther()方法");
        mv.setViewName("other");
        return mv;
    }

    //不指定请求方式,没有限制
    @RequestMapping(value = {"/other.do","/second.do"})
    public ModelAndView doFirst(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","欢迎使用springmvc做web开发");
        mv.addObject("fun","执行的是doFirst()方法");
        mv.setViewName("other");
        return mv;
    }
}
