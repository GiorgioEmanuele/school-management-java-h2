package com.students.controller;

import com.students.dto.StudentDTO;
import com.students.entity.Student;
import com.students.mapper.StudentMapper;
import com.students.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final MessageSource messageSource;

    @Autowired
    public StudentController(StudentService studentService, StudentMapper studentMapper, MessageSource messageSource) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.messageSource = messageSource;
    }

    @GetMapping("/getStudents")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        if (studentService.getStudents().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentMapper.studentsToDTOs(studentService.getStudents()));
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody @Valid Student student,
                                             @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage(
                "studentSavedSuccess", null
                , locale));
    }

    @DeleteMapping("/deleteStudent")
    public ResponseEntity<String> deleteStudent(@RequestParam Integer studentId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        Boolean isStudentDeleted = studentService.deleteStudent(studentId);

        if (isStudentDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage(
                    "studentDeletedSuccessfully", null, locale));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage(
                "studentNotFound", null, locale));
    }
}
