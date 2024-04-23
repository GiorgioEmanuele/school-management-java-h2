package com.students.repository;

import com.students.entity.Student;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void StudentRepository_Save_ReturnSavedStudent() {
        //arrange
        Student student = Student.builder().name("Emanuele").email("emanuele@gmail.com").build();
        //act
        Student savedStudent = studentRepository.save(student);
        //assert
        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getId()).isNotNull();
    }

    @Test
    public void StudentRepository_FindAll_ReturnOneOrMoreStudent() {
        Student student = Student.builder().name("Emanuele").email("emanuele@gmail.com").build();
        Student student2 = Student.builder().name("Emanuele").email("emanuele@gmail.com").build();

        studentRepository.save(student);
        studentRepository.save(student2);

        List<Student> savedStudents = studentRepository.findAll();

        Assertions.assertThat(savedStudents).isNotNull();
        Assertions.assertThat(savedStudents.size()).isGreaterThan(0);

    }

    @Test
    public void StudentRepository_DeleteStudent_ReturnDeletedStudent() {
        Student student = Student.builder().name("Emanuele").email("emanuele@gmail.com").build();

        studentRepository.save(student);

        studentRepository.deleteById(student.getId());

        Optional<Student> studentDeleted = studentRepository.findById(student.getId());

        Assertions.assertThat(studentDeleted).isEmpty();
    }

    @Test
    public void StudentRepository_UpdateStudent_ReturnUpdatedStudent() {
        Student student = Student.builder().name("Emanuele").email("emanuele@gmail.com").build();

        studentRepository.save(student);

        Student studentInput = Student.builder().id(1).name("Giorgio").email("g.capuno@outlook" +
                ".it").build();

        student.setName(studentInput.getName());
        student.setEmail(studentInput.getEmail());

        studentRepository.save(student);

        Assertions.assertThat(student.getName()).isEqualTo(studentInput.getName());
        Assertions.assertThat(student.getEmail()).isEqualTo(studentInput.getEmail());
    }
}
