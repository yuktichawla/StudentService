package com.steerlean.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;

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
}
