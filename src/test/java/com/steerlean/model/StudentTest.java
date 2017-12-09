package com.steerlean.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void getId() throws Exception {
        Student student = new Student("111", null, null, null);
        Assert.assertEquals("111", student.getId());
    }

    @Test
    public void setId() throws Exception {
        Student student = new Student("111", null, null, null);
        student.setId("234");
        Assert.assertEquals("234", student.getId());
    }

    @Test
    public void getName() throws Exception {
        Student student = new Student("111", "Balwant Rai", null, null);
        Assert.assertEquals("Balwant Rai", student.getName());
    }

    @Test
    public void setName() throws Exception {
        Student student = new Student("111", "Balwant Rai", null, null);
        student.setName("Sunny Deol");
        Assert.assertEquals("Sunny Deol", student.getName());
    }

    @Test
    public void getDescription() throws Exception {
        Student student = new Student("111", null, "good", null);
        Assert.assertEquals("good", student.getDescription());
    }

    @Test
    public void setDescription() throws Exception {
        Student student = new Student("111", null, "good", null);
        student.setDescription("cool");
        Assert.assertEquals("cool", student.getDescription());
    }

    @Test
    public void getCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("123", null, null, null));
        Student student = new Student("111", null, "good", courses);
        Assert.assertEquals(1, student.getCourses().size());
        Assert.assertEquals("123", courses.get(0).getId());
    }

    @Test
    public void setCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("123", null, null, null));
        Student student = new Student("111", null, "good", null);
        student.setCourses(courses);
        Assert.assertEquals(1, student.getCourses().size());
        Assert.assertEquals("123", courses.get(0).getId());
    }

    @Test
    public void testToString() throws Exception {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("123", null, null, null));
        Student student = new Student("111", null, "good", courses);
        String studentStr = "Student [id=111, name=null, description=good, courses=[Course [id=123, name=null, description=null, steps=null]]]";
        Assert.assertEquals(studentStr, student.toString());
    }

}
