package com.steerlean.repo;

import com.steerlean.model.Student;

import java.util.Map;

public interface StudentRepository {
    void saveStudent(Student person);

    void updateStudent(Student student);

    Student findStudent(String id);

    Map<String, Student> findAllStudents();

    void deleteStudent(String id);
}
