package com.bjpowernode.controller;

import com.bjpowernode.exception.AgeException;
import com.bjpowernode.exception.MyUserException;
import com.bjpowernode.exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value = "/some.do")
    public ModelAndView doSome(String username, Integer userage) throws MyUserException{
        ModelAndView mv = new ModelAndView();

        //根据请求参数抛出异常
        if (!"zhangsan".equals(username)){
            throw new NameException("姓名不正确");
        }

        if (userage == null || userage > 80){
            throw new AgeException("年龄比较大");
        }

        mv.addObject("username",username);
        mv.addObject("userage",userage);
        mv.setViewName("show");
        return mv;
    }

}