package com.bjpowernode.handler;

import com.bjpowernode.exception.AgeException;
import com.bjpowernode.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllerAdvice: 控制器增强( 也就是说给控制器类增加功能--异常处理功能 )
 *                    位置: 在类的上面
 *                    特点:必须让框架知道这个注解所在的包名，需要在springmvc配置文件中声明组件扫描器
 *                        指定@ControllerAdvice所在的包名
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //定义方法，处理发生的异常
    //处理异常的方法和控制器方法的定义一样，可以有多个参数，可以有ModelAndView,String,void,Object类型的返回值
    //形参:Exception 表示Controller中抛出的异常对象，通过形参可以获取发生异常的信息
    //@ExceptionHandler(异常的class) : 表示异常的类型，当发生此类型异常时，由当前方法来处理
    @ExceptionHandler(value = NameException.class)
    public ModelAndView doNameException(Exception e){
        //处理NameException的异常
        /**
         * 异常发生处理逻辑:
         *  1.需要把异常记录下来，记录到数据库，日志文件。
         *    记录日志发生的时间，哪个方法发生的异常，异常错误内容。
         *  2.发送通知，把异常的信息通过邮件，短信，微信发送给相关人员。
         *  3.给用户友好的提示。
         */
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","姓名必须是zhangsan，其他用户不能访问");
        mv.addObject("exception",e);
        mv.setViewName("nameError");
        return mv;
    }

    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception e){
        //处理NameException的异常
        /**
         * 异常发生处理逻辑:
         *  1.需要把异常记录下来，记录到数据库，日志文件。
         *    记录日志发生的时间，哪个方法发生的异常，异常错误内容。
         *  2.发送通知，把异常的信息通过邮件，短信，微信发送给相关人员。
         *  3.给用户友好的提示。
         */
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","您的年龄不能大于80");
        mv.addObject("exception",e);
        mv.setViewName("ageError");
        return mv;
    }

    //处理其他异常，NameException,AgeException以外的，不知类型的异常
    //没有value的ExceptionHandler注解只能有一个
    @ExceptionHandler
    public ModelAndView doOtherException(Exception e){
        //处理其他类型的异常
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","未知异常信息，请联系管理员处理");
        mv.addObject("exception",e);
        mv.setViewName("defaultError");
        return mv;
    }
}
