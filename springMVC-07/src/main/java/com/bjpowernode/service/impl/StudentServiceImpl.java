package com.bjpowernode.service.impl;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.entity.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    //引用类型自动注入@Autowired,@Resource
    @Resource
    private StudentDao studentDao;

    @Override
    public int addStudent(Student student) {
        int count = studentDao.insertStudent(student);
        return count;
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> studentList = studentDao.selectStudents();
        return studentList;
    }
}
