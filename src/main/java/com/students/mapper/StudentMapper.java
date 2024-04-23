package com.students.mapper;

import com.students.dto.StudentDTO;
import com.students.entity.Student;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentMapper {

    public List<StudentDTO> studentsToDTOs(List<Student> students) {
        List<StudentDTO> studentsDTOs = new ArrayList<>();
        students.forEach(student -> {
            StudentDTO studentDTO = new StudentDTO(student.getName(), student.getEmail());
            studentsDTOs.add(studentDTO);
        });
        return studentsDTOs;
    }
}
