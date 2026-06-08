package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.testng.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void getUsers() {
        Response response = RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com/todos/1")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
    }

    @Test
    public void postUser() {
        String requestBody = "{\n" +
                "  \"title\": \"Test Post\",\n" +
                "  \"body\": \"This is a test post request\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .basePath("/posts")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("POST Response:");
        System.out.println(response.asString());
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    @Test(priority = 1)
    public void loginTest() {
        System.out.println("Login Test");
    }

    @Test(priority = 2)
    public void searchTest() {
        System.out.println("Search Test");
    }

    @Test(priority = 3)
    public void logoutTest() {
        System.out.println("Logout Test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }
}
