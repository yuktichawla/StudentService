package com.steerlean.repo;

import com.steerlean.model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentRepositoryImplTest {

    @Mock
    private RedisTemplate<String, Object> mockRedisTemplate;

    @Mock
    private HashOperations mockHashOperations;

    @InjectMocks
    private StudentRepositoryImpl studentRepository;

    @Before
    public void setup() {
        Mockito.when(mockRedisTemplate.opsForHash()).thenReturn(mockHashOperations);
    }

    @Test
    public void testSaveStudent() throws Exception {
        Student stubStudent = new Student("1", null, null, null);
        studentRepository.saveStudent(stubStudent);
        Mockito.verify(mockHashOperations, Mockito.times(1)).put("Student", "1", stubStudent);
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student stubStudent = new Student("1", null, null, null);
        studentRepository.updateStudent(stubStudent);
        Mockito.verify(mockHashOperations, Mockito.times(1)).put("Student", "1", stubStudent);
    }

    @Test
    public void testFindStudent() throws Exception {
        Student stubStudent = new Student("1", null, null, null);
        Mockito.when(mockHashOperations.get("Student", "1")).thenReturn(stubStudent);
        Student actualStudent = studentRepository.findStudent("1");
        Assert.assertEquals(stubStudent, actualStudent);
        Mockito.verify(mockHashOperations, Mockito.times(1)).get("Student", "1");
    }

    @Test
    public void testFindAllStudents() throws Exception {
        Map dummyStudents = Mockito.mock(Map.class);
        Mockito.when(mockHashOperations.entries("Student")).thenReturn(dummyStudents);
        Map<String, Student> actualStudentsMap = studentRepository.findAllStudents();
        Assert.assertEquals(dummyStudents, actualStudentsMap);
        Mockito.verify(mockHashOperations, Mockito.times(1)).entries("Student");
    }

    @Test
    public void testDeleteStudent() throws Exception {
        studentRepository.deleteStudent("1");
        Mockito.verify(mockHashOperations, Mockito.times(1)).delete("Student", "1");
    }

}