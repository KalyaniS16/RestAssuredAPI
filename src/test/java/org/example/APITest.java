package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

    @Test
    public void test1() {
        Response response = RestAssured.get("https://gorest.co.in/public/v2/users");
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());
        System.out.println("Status Line : " + response.getStatusLine());
        System.out.println("Time taken : " + response.getTime());
        System.out.println("Body : " + response.getBody().asString());
        System.out.println("Header : " + response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code is 200 but found " + statusCode);
    }

    @Test
    void test2(){
        RestAssured.given().get("https://gorest.co.in/public/v2/users").then().statusCode(200);
        System.out.println("Test 2 executed successfully");
    }

    @Test
    void test3(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
        RestAssured.given().param("userId", 1).when().get().then().assertThat().statusCode(200) ;
        System.out.println("Test 3 executed successfully");
    }
}
