package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String username, Integer userage){
        ModelAndView mv = new ModelAndView();

        mv.addObject("username",username);
        mv.addObject("userage",userage);
        mv.setViewName("show");
        return mv;
    }

}