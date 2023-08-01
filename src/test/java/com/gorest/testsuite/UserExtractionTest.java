package com.gorest.testsuite;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    // Extract All IDS
    @Test
    public void allId() {
        List<String> ListOfIds = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + ListOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract All names
    @Test
    public void allNames() {
        List<String> ListOfName = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + ListOfName);
        System.out.println("------------------End of Test---------------------------");
    }

    // Extract the name of 5th object
    @Test
    public void fifthObject() {
        String name = response.extract().path("name[5]");
        System.out.println("Extract the name of 5th store " + name);
    }

    //Extract the names of all object whose status = inactive
    @Test
    public void statusInActive() {
        List<String> name = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("Find the names of all object " + name);
    }

    //Extract the gender of all the object whose status = active
    @Test
    public void genderActive() {
        List<String> gender = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("Find the gender of all the object " + gender);
    }

//Print the names of the object whose gender = female

    @Test
    public void genderFemale() {
        List<String> female = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("Find the names of the object whose gender " + female);
    }

    //Get all the emails of the object where status = inactive
    @Test
    public void statusEmail() {
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("Find all the emails of the object " + email);
    }

    //Get the ids of the object where gender = male
    @Test
    public void idsOfMale() {
        List<String> male = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("Find all the emails of the object " + male);
    }

    //Get all the status
    @Test
    public void allStatus() {
        List<String> ListOfStatus = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + ListOfStatus);
        System.out.println("------------------End of Test---------------------------");
    }
//Get email of the object where name = Sarala Menon

    @Test
    public void emailOfObject() {
        List<String> emailName = response.extract().path("findAll{it.name == 'Sarala Menon'}.email");
        System.out.println("Find all the email of the object " + emailName);
    }

    //Get gender of id = 5471
    @Test
    public void genderOfId() {
        String gender = response.extract().path("find{it.id == 4040692}.gender");
        System.out.println("gender of id " +gender);

    }
}