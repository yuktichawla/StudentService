package com.steerlean.repo;

import com.steerlean.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private static final String KEY = "Student";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveStudent(final Student student) {
        hashOperations.put(KEY, student.getId(), student);
    }

    @Override
    public void updateStudent(final Student student) {
        hashOperations.put(KEY, student.getId(), student);
    }

    @Override
    public Student findStudent(final String id) {
        return (Student) hashOperations.get(KEY, id);
    }

    @Override
    public Map<String, Student> findAllStudents() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void deleteStudent(final String id) {
        hashOperations.delete(KEY, id);
    }
}
