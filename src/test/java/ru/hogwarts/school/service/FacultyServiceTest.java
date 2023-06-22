package ru.hogwarts.school.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;


class FacultyServiceTest {
    private FacultyService out;

    @BeforeEach
    void beforeEach() {
        out = new FacultyService();
        out.addFaculty(new Faculty(1, "first", "green"));
        out.addFaculty(new Faculty(2, "second", "green"));
        out.addFaculty(new Faculty(3, "third", "red"));
    }

    @Test
    void addFacultyTest() {
        Faculty student = new Faculty(0, "faculty", "green");
        Assertions.assertThat(out.addFaculty(student)).isEqualTo(new Faculty(4, "faculty", "green"));
    }

    @Test
    void editTest() {
        Faculty faculty = new Faculty(4, "forth", "green");
        out.addFaculty(faculty);
        assertThat(out.editFaculty(new Faculty(4, "forth", "green"))).isEqualTo(out.getFaculty(4));
    }

    @Test
    void editNegativeTest() {
        assertThat(out.editFaculty(new Faculty(4, "forth", "green"))).isNull();
    }

    @Test
    void getTest() {
        assertThat(out.getFaculty(1)).isEqualTo(new Faculty(1, "first", "green"));
    }

    @Test
    void removeTest() {
        out.removeFaculty(2);
        assertThat(out.getFaculty(2)).isNull();
    }

    @Test
    void filterTest() {
        assertThat(out.filterByColor("green"))
                .hasSize(2)
                .containsExactlyInAnyOrder(
                        new Faculty(1, "first", "green"),
                        new Faculty(2, "second", "green")
                );
    }
}
