package com.example.vidium.controller;

import com.example.vidium.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Driss", 68),
            new Student(2, "Kevin", 50)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }
}
