package com.steerlean.controller;

import com.steerlean.model.Course;
import com.steerlean.model.Student;
import com.steerlean.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class StudentControllerTest {
    @Mock
    private StudentService mockStudentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void testRetrieveCoursesForStudent() throws Exception {
        List<Course> dummyCourses = Mockito.mock(List.class);
        Mockito.when(mockStudentService.retrieveCourses("1")).thenReturn(dummyCourses);
        List<Course> actualCourses = studentController.retrieveCoursesForStudent("1");
        Mockito.verify(mockStudentService, Mockito.times(1)).retrieveCourses("1");
        Assert.assertEquals(dummyCourses, actualCourses);
    }

    @Test
    public void testRetrieveDetailsForCourse() throws Exception {
        Course dummyCourse = Mockito.mock(Course.class);
        Mockito.when(mockStudentService.retrieveCourse("1", "agri")).thenReturn(dummyCourse);
        Course actualCourse = studentController.retrieveDetailsForCourse("1", "agri");
        Mockito.verify(mockStudentService, Mockito.times(1)).retrieveCourse("1", "agri");
        Assert.assertEquals(dummyCourse, actualCourse);
    }

    @Test
    public void testRetrieveStudent() throws Exception {
        Student dummyStudent = Mockito.mock(Student.class);
        Mockito.when(mockStudentService.retrieveStudent("1")).thenReturn(dummyStudent);
        Student actualStudent = studentController.retrieveStudent("1");
        Assert.assertEquals(dummyStudent, actualStudent);
        Mockito.verify(mockStudentService, Mockito.times(1)).retrieveStudent("1");
    }

    @Test
    public void testRetrieveAllStudents() throws Exception {
        List<Student> dummyStudents = Mockito.mock(List.class);
        Mockito.when(mockStudentService.retrieveAllStudents()).thenReturn(dummyStudents);
        List<Student> actualStudents = studentController.retrieveAllStudents();
        Assert.assertEquals(dummyStudents, actualStudents);
        Mockito.verify(mockStudentService, Mockito.times(1)).retrieveAllStudents();
    }

    @Test
    public void testRegisterStudentForCourseWhenStudentMissing() {
        Mockito.when(mockStudentService.addCourse("1", null)).thenReturn(null);
        ResponseEntity<Void> responseEntity = studentController.registerStudentForCourse("1", null);
        Assert.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testRegisterStudentForCourse() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        Course stubCourse = new Course("agri", null, null, null);
        Mockito.when(mockStudentService.addCourse("1", stubCourse)).thenReturn(stubCourse);

        ResponseEntity<Void> responseEntity = studentController.registerStudentForCourse("1", stubCourse);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}