package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
    }
    // Extract the title
    @Test
    public void allTitles() {
        List<String> ListOfTitle = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + ListOfTitle);
        System.out.println("------------------End of Test---------------------------");
    }
//Extract the total number of record
    @Test
    public void allRecord() {
        List<String> ListOfId = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + ListOfId);
        System.out.println("------------------End of Test---------------------------");
    }
//Extract the body of 15th record
    @Test
    public void bodyOfFifteen() {
        String body = response.extract().path("body[5]");
        System.out.println("Extract the  body of 15th record " + body);
    }
//Extract the user_id of all the records

    @Test
    public void user_Id() {
        List<String> ListOfUser_Id = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + ListOfUser_Id);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the title of all records
    @Test
    public void allTitlesOfAllRecords() {
        List<String> ListOfTitle = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + ListOfTitle);
        System.out.println("------------------End of Test---------------------------");
    }
//Extract the title of all records whose user_id = 4040706
    @Test
    public void statusInActive() {
        List<String> title = response.extract().path("findAll{it.user_id == 4040706}.title");
        System.out.println("Find the names of all object " + title);
    }
//Extract the body of all records whose id = 4040706
@Test
public void bodyOfRecords() {
    List<String> body = response.extract().path("findAll{it.user_id == 4040706}.body");
    System.out.println("Find the names of all object " + body);
}




}
