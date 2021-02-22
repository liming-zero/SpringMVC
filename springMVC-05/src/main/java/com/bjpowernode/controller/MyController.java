package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    /**
     * 处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
     */
    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String username, Integer userage) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username",username);
        mv.addObject("userage",userage);
        mv.setViewName("show");
        return mv;
    }
}