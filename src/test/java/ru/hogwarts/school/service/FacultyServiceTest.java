package ru.hogwarts.school.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    private FacultyService out;
    private List<Faculty> faculties;

    @BeforeEach
    void beforeEach() {
        faculties = new ArrayList<>();
        faculties.add(new Faculty(1, "first", "green"));
        faculties.add(new Faculty(2, "second", "green"));
        faculties.add(new Faculty(3, "third", "red"));
    }

    @Test
    void addFacultyTest() {
        Faculty student = new Faculty(0, "faculty", "green");
        when(facultyRepository.save(student)).thenReturn(student);
        Assertions.assertThat(out.addFaculty(new Faculty(0, "faculty", "green")))
                .isEqualTo(student);
    }

    @Test
    void editTest() {
        Faculty student = new Faculty(0, "faculty", "green");
        when(facultyRepository.save(student)).thenReturn(student);
        Assertions.assertThat(out.editFaculty(new Faculty(0, "faculty", "green")))
                .isEqualTo(student);
    }

    @Test
    void getTest() {
        when(facultyRepository.findById(1L)).thenReturn(Optional.ofNullable(faculties.get(0)));
        assertThat(out.getFaculty(1)).isEqualTo(faculties.get(0));
    }


    @Test
    void filterTest() {
        when(facultyRepository.findAll()).thenReturn(faculties);
        assertThat(out.filterByColor("green"))
                .containsExactlyInAnyOrder(
                        new Faculty(1, "first", "green"),
                        new Faculty(2, "second", "green")
                );
    }
}
