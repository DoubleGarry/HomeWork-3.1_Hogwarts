package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long countId = 0;

    public Student addStudent(Student student) {
        student.setId(++countId);
        students.put(countId, student);
        return student;
    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student removeStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> filterByAge(Integer age) {
        return students.values().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());

        }
    }
