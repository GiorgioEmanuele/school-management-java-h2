package com.students.service;

import com.students.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    List<Student> getStudents();

    void addStudent(Student student);

    Boolean deleteStudent(Integer studentId);
}
