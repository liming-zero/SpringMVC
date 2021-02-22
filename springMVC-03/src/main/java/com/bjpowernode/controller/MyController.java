package com.bjpowernode.controller;

import com.bjpowernode.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    /**
     * 逐个接收请求参数
     *  要求:处理器方法的形参名和请求中参数名必须一致
     *      同名的请求参数赋值给同名的形参
     *  框架接收请求的参数
     *      1.使用request对象接收请求参数
     *        String username = request.getParameter("username");
     *        String userage = request.getParameter("userage");
     *      2.springmvc通过中央调用器DispatcherServlet 调用 MyController的doSome()方法
     *        调用方法时，按名称对应，把接收的参数赋值给形参 doSome(username, Integer.valueOf(userage));
     *        框架会提供自动转换的功能，把String类型转换为其他类型
     * @return
     */
    @RequestMapping(value ="/receivproperty.do")
    public ModelAndView doSome(String username,Integer userage){
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName",username);
        mv.addObject("myAge",userage);
        mv.setViewName("show");
        return mv;
    }

    /**
     * 请求中的参数名和方法的形参名不一样，使用对象接收
     * @RequestParam: 解决此问题
     *      属性: 1.value:请求参数名     位置:在形参的前面
     *           2.required: 是一个boolean类型，默认是true，表示请求中必须包含此参数
     */
    @RequestMapping(value ="/receivparam.do")
    public ModelAndView receivParam(@RequestParam(value = "rusername",required = false) String username,
                                    @RequestParam(value = "ruserage",required = false) Integer userage){
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName",username);
        mv.addObject("myAge",userage);
        mv.setViewName("show");
        return mv;
    }

    /**
     * 处理器方法形参是java对象，这个对象的属性名和请求中参数名是一样的
     * 框架会创建形参的java对象，给属性赋值。请求中的参数是username,框架会调用setUsername()
     * @return
     */
    @RequestMapping(value ="/receivobject.do")
    public ModelAndView receivObject(User user){
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName",user.getUsername());
        mv.addObject("myAge",user.getUserage());
        mv.addObject("myUser",user);
        mv.setViewName("show");
        return mv;
    }

}
