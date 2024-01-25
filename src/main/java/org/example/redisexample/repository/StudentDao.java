package org.example.redisexample.repository;

import org.example.redisexample.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Repository
public class StudentDao {

    public static final String HASH_KEY = "Student";

    @Autowired
    private RedisTemplate template;

    public Student save(Student student){
        System.out.println("Inside save method");
        template.opsForHash().put(HASH_KEY,student.getId(),student);
        return student;
    }

    public List<Student> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Student findStudentById(long id){
        return (Student) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteStudent(long id){
        template.opsForHash().delete(HASH_KEY,id);
        return "student removed !!";
    }


}
