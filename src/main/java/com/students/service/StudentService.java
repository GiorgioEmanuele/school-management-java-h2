package com.students.service;

import com.students.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();

    Student getStudentById(Integer studentId);

    void addStudent(Student student);

    Boolean deleteStudentById(Integer studentId);

    Boolean updateStudent(Student student);

    void uploadFileOfStudents(List<Student> students);

    //da fare
    //Boolean deleteGroupOfStudentsByIds(List<Integer> studentsIds);
    //Boolean updateGroupOfStudents(List<Student> students);

}
