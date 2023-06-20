package ru.hogwarts.school.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.*;

class StudentServiceTest {
    private StudentService out;

    @BeforeEach
    void beforeEach() {
        out = new StudentService();
        out.addStudent(new Student(1, "first", 12));
        out.addStudent(new Student(2, "second", 12));
        out.addStudent(new Student(3, "third", 11));
    }

    @Test
    void createTest() {
        Student student = new Student(0, "Potter", 10);
        Assertions.assertThat(out.addStudent(student)).isEqualTo(new Student(4, "Potter", 10));
    }

    @Test
    void updateTest() {
        Student student = new Student(4, "forth", 14);
        out.addStudent(student);
        assertThat(out.editStudent(new Student(4, "forth", 10))).isEqualTo(out.getStudent(4));
    }

    @Test
    void editNegativeTest() {
        assertThat(out.editStudent(new Student(4, "forth", 10))).isNull();
    }

    @Test
    void getTest() {
        assertThat(out.getStudent(1)).isEqualTo(new Student(1, "first", 12));
    }

    @Test
    void removeTest() {
        out.removeStudent(2);
        assertThat(out.getStudent(2)).isNull();
    }

    @Test
    void filterTest() {
        assertThat(out.filterByAge(12))
                .hasSize(2)
                .containsExactlyInAnyOrder(
                        new Student(1, "first", 12),
                        new Student(2, "second", 12)
                );
    }
}
