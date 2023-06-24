package ru.hogwarts.school.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
   private StudentService out;
    private List<Student> students;

    @BeforeEach
    void beforeEach() {
        students = new ArrayList<>();
        students.add(new Student(1, "first", 12));
        students.add(new Student(2, "second", 12));
        students.add(new Student(3, "third", 11));
    }

    @Test
    void createTest() {
        Student student = new Student(1, "first", 12);
        when(studentRepository.save(student)).thenReturn(student);
        Assertions.assertThat(out.addStudent(new Student(1, "first", 12)))
                .isEqualTo(student);

    }

    @Test
    void editTest() {
        Student student = new Student(1, "first", 12);
        when(studentRepository.save(student)).thenReturn(student);
        Assertions.assertThat(out.editStudent(new Student(1, "first", 12)))
                .isEqualTo(student);
    }

    @Test
    void editNegativeTest() {
        assertThat(out.editStudent(new Student(4, "forth", 10))).isNull();
    }

    @Test
    void getTest() {
        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(students.get(0)));
        assertThat(out.getStudent(1)).isEqualTo(students.get(0));
    }

    @Test
    void filterTest() {
        when(studentRepository.findAll()).thenReturn(students);
        assertThat(out.filterByAge(12))
                .containsExactlyInAnyOrder(
                        new Student(1, "first", 12),
                        new Student(2, "second", 12)
                );
    }
}
