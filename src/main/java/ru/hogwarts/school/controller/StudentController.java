package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student add(Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> edit(Student student) {
        Student editStudent = studentService.editStudent(student);
        if (editStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> get(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter//{age}")
    public Collection<Student> filter(@PathVariable int age) {
        return studentService.filterByAge(age);
    }
}
