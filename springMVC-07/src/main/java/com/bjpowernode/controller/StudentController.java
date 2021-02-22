package com.bjpowernode.controller;

import com.bjpowernode.entity.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    //声明Service
    @Resource
    private StudentService studentService;

    //注册学生
    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){
        ModelAndView mv = new ModelAndView();
        String message = "注册失败";
        //调用service处理student
        int count = studentService.addStudent(student);
        if (count > 0){
            //注册成功
            message = "学生【" +student.getName()+ "】注册成功";
        }
        //添加数据
        mv.addObject("msg",message);
        //指定结果页面
        mv.setViewName("result");
        return mv;
    }

    //处理查询，响应AJAX
    @RequestMapping("/selectStudents.do")
    @ResponseBody
    public List<Student> queryStudent(){
        //参数检查，简单的数据处理
        List<Student> studentList = studentService.queryStudents();
        return studentList;
    }
}
