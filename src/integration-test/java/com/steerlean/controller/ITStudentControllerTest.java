package com.steerlean.controller;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITStudentControllerTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void testRetrieveDetailsForCourse() {
        when().
                get("/students/{studentId}/courses/{courseId}", "Student1", "Course1").
                then().
                statusCode(HttpStatus.OK.value()).
                body("name", Matchers.is("Spring")).
                body("description", Matchers.is("10 Steps"));
    }

    @Test
    public void testRetrieveCoursesForStudent() {
        when().
                get("/students/{studentId}/courses", "Student1").
                then().
                statusCode(HttpStatus.OK.value()).
                body("name", Matchers.hasItems("Spring", "Spring MVC", "Spring Boot", "Maven")).
                body("description", Matchers.hasItems("10 Steps", "10 Examples", "6K Students", "Most popular maven course on internet!"));
    }

    @Test
    public void testRetrieveCoursesForStudent_Strict() {
        String coursesJsonStr = when().get("/students/{studentId}/courses", "Student1").asString();
        io.restassured.path.json.JsonPath from = from(coursesJsonStr);
        Map<String, Object> firstCourse = from.get("[0]");
        List<Object> responseJsonObjectList = from.get();
        Assert.assertEquals(4, responseJsonObjectList.size());
        Assert.assertEquals("Spring", firstCourse.get("name"));
        Assert.assertEquals("10 Steps", firstCourse.get("description"));
    }

    @Test
    public void testRetrieveStudent() {
        when().
                get("/student/{studentId}", "Student1").
                then().
                statusCode(HttpStatus.OK.value()).
                body("name", Matchers.is("Ranga Karanam")).
                body("description", Matchers.is("Hiker, Programmer and Architect"));
    }

    @Test
    public void testRetrieveAllStudents() {
        when().
                get("/students").
                then().
                statusCode(HttpStatus.OK.value()).
                body("name", Matchers.hasItems("Ranga Karanam", "Satish T")).
                body("description", Matchers.hasItems("Hiker, Programmer and Architect", "Hiker, Programmer and Architect"));
    }

    @Test
    public void testRegisterStudent() {
        Map<String, String> studentMap = new HashMap<>();
        studentMap.put("id", "Student3");
        studentMap.put("name", "Balwant Rai");
        studentMap.put("description", "aspiring lawyer");
        studentMap.put("courses", null);

        given().
                contentType("application/json").
                body(studentMap).
                when().
                post("/students").
                then().
                statusCode(HttpStatus.CREATED.value());

        ValidatableResponse validatableResponse = when().
                get("/students").
                then().
                statusCode(HttpStatus.OK.value()).
                body("size()", Matchers.is(3));

        validatableResponse.
                body("find {it.name == 'Balwant Rai'}.description", Matchers.is("aspiring lawyer"));
    }
}
