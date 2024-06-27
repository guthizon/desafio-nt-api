package com.apitests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JsonPlaceholderTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    public static void setUp() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterAll
    public static void tearDown() {
        extent.flush();
    }

    @Test
    public void testGetPostPositive() {
        test = extent.createTest("testGetPostPositive", "Teste positivo para GET de um post");

        Response response = given()
                .when()
                .get(BASE_URL + "/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .extract().response();

        test.pass("Teste positivo para GET de um post passou");
    }

    @Test
    public void testGetPostNegative() {
        test = extent.createTest("testGetPostNegative", "Teste negativo para GET de um post");

        given()
                .when()
                .get(BASE_URL + "/posts/0")
                .then()
                .statusCode(404);

        test.pass("Teste negativo para GET de um post passou");
    }

    @Test
    public void testPostCreatePositive() {
        test = extent.createTest("testPostCreatePositive", "Teste positivo para POST de um post");

        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .extract().response();

        test.pass("Teste positivo para POST de um post passou");
    }

    @Test
    public void testPostCreateNegative() {
        test = extent.createTest("testPostCreateNegative", "Teste negativo para POST de um post");

        String requestBody = "{ \"title\": \"\", \"body\": \"\", \"userId\": -120, }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/posts")
                .then()
                .statusCode(500);

        test.pass("Teste negativo para POST de um post passou");
    }

    @Test
    public void testPutUpdatePositive() {
        test = extent.createTest("testPutUpdatePositive", "Teste positivo para PUT de um post");

        String requestBody = "{ \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(BASE_URL + "/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("updated title"))
                .body("body", equalTo("updated body"))
                .body("userId", equalTo(1))
                .extract().response();

        test.pass("Teste positivo para PUT de um post passou");
    }

    @Test
    public void testPutUpdateNegative() {
        test = extent.createTest("testPutUpdateNegative", "Teste negativo para PUT de um post");

        String requestBody = "{ \"title\": \"\", \"body\": \"\", \"userId\": -1 }";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(BASE_URL + "/posts/")
                .then()
                .statusCode(404);

        test.pass("Teste negativo para PUT de um post passou");
    }

    @Test
    public void testDeletePostPositive() {
        test = extent.createTest("testDeletePostPositive", "Teste positivo para DELETE de um post");

        given()
                .when()
                .delete(BASE_URL + "/posts/1")
                .then()
                .statusCode(200);

        test.pass("Teste positivo para DELETE de um post passou");
    }

    @Test
    public void testDeletePostNegative() {
        test = extent.createTest("testDeletePostNegative", "Teste negativo para DELETE de um post");

        given()
                .when()
                .delete(BASE_URL + "/posts/")
                .then()
                .statusCode(404);

        test.pass("Teste negativo para DELETE de um post passou");
    }
}
