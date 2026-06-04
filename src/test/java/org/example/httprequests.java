package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class httprequests {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    /**
     * GET Method - Retrieve a single post
     */
    @Test(priority = 1)
    public void getMethod() {
        System.out.println("===== Executing GET Method =====");

        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/posts/1")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("GET Request successful!");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "GET request should return 200 OK");
        Assert.assertNotNull(response.jsonPath().get("id"), "Response should contain post ID");
    }

    /**
     * POST Method - Create a new post
     */
    @Test(priority = 2)
    public void postMethod() {
        System.out.println("===== Executing POST Method =====");

        String requestBody = "{\n" +
                "  \"title\": \"New Post Created\",\n" +
                "  \"body\": \"This is a newly created post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/posts")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("POST Request successful!");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 201, "POST request should return 201 Created");
    }

    /**
     * PUT Method - Update an existing post (full replacement)
     */
    @Test(priority = 3)
    public void putMethod() {
        System.out.println("===== Executing PUT Method =====");

        String requestBody = """
            {
                "title": "New Post updated",
                "body": "This is a newly updated request from post to put",
                "userId": 1
            }
            """;

        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/posts/1")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("PUT Request successful!");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "PUT request should return 200 OK");
    }

    /**
     * PATCH Method - Partially update an existing post
     */
    @Test
    public void patchMethod() {
        System.out.println("===== Executing PATCH Method =====");

        String requestBody = "{\n" +
                "  \"title\": \"Only Title Updated with PATCH\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/posts/1")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch()
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("PATCH Request successful!");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "PATCH request should return 200 OK");
    }

    /**
     * DELETE Method - Delete a post
     */
    @Test(priority = 4)
    public void deleteMethod() {
        System.out.println("===== Executing DELETE Method =====");

        Response response = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath("/posts/1")
                .when()
                .delete()
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("DELETE Request successful!");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "DELETE request should return 200 OK");
    }
}
