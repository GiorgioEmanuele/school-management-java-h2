package com.students.service;

import com.students.entity.Student;
import com.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Boolean deleteStudentById(Integer studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateStudent(Student myStudent) {
        Optional<Student> currentStudent = studentRepository.findById(myStudent.getId());
        if (currentStudent.isPresent()) {
            Student updatedStudent = currentStudent.get();
            updatedStudent.setName(myStudent.getName());
            updatedStudent.setEmail(myStudent.getEmail());
            studentRepository.save(updatedStudent);
            return true;
        }
        return false;
    }
}
