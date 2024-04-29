package com.students.controller;

import com.students.entity.Student;
import com.students.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Validated
@RestController
@RequestMapping("/api/student")
public class StudentController {

  private final StudentService studentService;
  private final MessageSource messageSource;

  @Autowired
  public StudentController(StudentService studentService, MessageSource messageSource) {
    this.studentService = studentService;
    this.messageSource = messageSource;
  }

  @GetMapping("/getStudents")
  public ResponseEntity<List<Student>> getAllStudents() {
    if (studentService.getStudents().isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudents());
  }

  @GetMapping("/getStudent")
  public ResponseEntity<Student> getStudenttById(@RequestParam Integer studentId) {
    Student student = studentService.getStudentById(studentId);
    if (student != null) {
      return ResponseEntity.status(HttpStatus.OK).body(student);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @PostMapping("/addStudent")
  public ResponseEntity<String> addStudent(@RequestBody @Valid Student student, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
    studentService.addStudent(student);
    return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage(
        "studentSavedSuccess", null, locale));
  }

  @DeleteMapping("/deleteStudent")
  public ResponseEntity<String> deleteStudent(@RequestParam Integer studentId,
                                              @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
    Boolean isStudentDeleted = studentService.deleteStudentById(studentId);
    if (isStudentDeleted) {
      return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage(
          "studentDeletedSuccessfully", null, locale));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage(
        "studentNotFound", null, locale));
  }

  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid Student student,
                                              @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
    Boolean isStudentUpdated = studentService.updateStudent(student);
    if (isStudentUpdated) {
      return ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("studentUpdated", null, locale));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("studentUpdateNotFound", null, locale));
  }

  @PostMapping("/uploadFileOfStudents")
  public ResponseEntity<String> addGroupOfStudents() {
    return null;
  }
}
