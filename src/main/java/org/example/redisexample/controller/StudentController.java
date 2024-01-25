package org.example.redisexample.controller;

import org.example.redisexample.entity.Student;
import org.example.redisexample.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentDao dao;

    @PostMapping
    public Student save(@RequestBody Student student) {
        return dao.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "Student")
    public Student findStudent(@PathVariable long id) {
        System.out.println("Inside student method");
        return dao.findStudentById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id",value = "Student")
    public String remove(@PathVariable long id)   {
        return dao.deleteStudent(id);
    }

}
