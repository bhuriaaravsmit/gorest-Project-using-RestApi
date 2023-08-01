package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();
    }

//Verify the if the total record is 20
    @Test
    public void total() {
        response.body("id.size()", equalTo(20));
    }

//Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”

    @Test
    public void nameOfId() {
        response.body("name[2]", equalTo("Tarun Rana"));
    }

//Check the single ‘Name’ in the Array list (Sharmila Embranthiri DC)

    @Test
    public void singleName() {
        response.body("name[4]", equalTo("Manikya Chaturvedi"));
    }
// Check the multiple ‘Names’ in the ArrayList (Sharmila Embranthiri DC, Buddhana Prajapat
//           , Keerti Embranthiri)

    @Test
    public void multipleName() {
        response.body("name", hasItems("Sharmila Embranthiri DC", "Tanushri Nayar",
                "Anjushree Abbott"));
    }

    //Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void emailOfUserId() {
        response.body("email[2]", equalTo("tarun_rana@lynch-oconner.example"));
    }

    //Verify the status is “Active” of user name is “Shanti Bhat V”

    @Test
    public void statusActive() {
        response.body("name", equalTo("Tanushri Nayar"));
    }

    //Verify the Gender = male of user name is “Niro Prajapat”

    @Test
    public void genderMale() {
        response.body("gender[1]", equalTo("male"));
    }

}

