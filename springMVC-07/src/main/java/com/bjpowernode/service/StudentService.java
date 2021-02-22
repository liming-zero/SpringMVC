package com.bjpowernode.service;

import com.bjpowernode.entity.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);

    List<Student> queryStudents();
}
