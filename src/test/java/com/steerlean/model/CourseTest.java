package com.steerlean.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourseTest {
    @Test
    public void getId() throws Exception {
        Course course = new Course("123", null, null, null);
        Assert.assertEquals("123", course.getId());
    }

    @Test
    public void setId() throws Exception {
        Course course = new Course("123", null, null, null);
        course.setId("345");
        Assert.assertEquals("345", course.getId());
    }

    @Test
    public void getDescription() throws Exception {
        Course course = new Course(null, null, "abd", null);
        Assert.assertEquals("abd", course.getDescription());
    }

    @Test
    public void getName() throws Exception {
        Course course = new Course("123", "Agri", null, null);
        Assert.assertEquals("Agri", course.getName());
    }

    @Test
    public void getSteps() throws Exception {
        List<String> mockList = Mockito.mock(List.class);
        Course course = new Course("123", null, null, mockList);
        Assert.assertEquals(mockList, course.getSteps());
    }

    @Test
    public void testToString() throws Exception {
        List<String> steps = new ArrayList<>();
        steps.add("first");
        steps.add("second");
        steps.add("third");
        Course course = new Course("123", "Agri", "Agriculture", steps);
        String expectedCourseStr = "Course [id=123, name=Agri, description=Agriculture, steps=[first, second, third]]";
        Assert.assertEquals(expectedCourseStr, course.toString());
    }

    @Test
    public void testEquals() throws Exception {
        Course course = new Course("123", "Agri", null, null);
        Course course2 = new Course("123", "Agri", null, null);
        Assert.assertEquals(course, course2);
    }

    @Test
    public void testNotEquals() {
        Course course = new Course("123", "Agri", null, null);
        Course course2 = new Course("342", "Agri", null, null);
        Assert.assertNotEquals(course, course2);
    }

    @Test
    public void testEqualsWhenOnlyIdMatches() {
        Course course = new Course("123", "Agri", null, null);
        Course course1 = new Course("123", "Agri1", null, null);
        Assert.assertEquals(course, course1);
    }

}
