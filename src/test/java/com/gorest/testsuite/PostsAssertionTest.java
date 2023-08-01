package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        //  RestAssured.port = 3030;
        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
    }

//Verify the if the total record is 25

    @Test
    public void total() {
        response.body("id.size()", equalTo(25));
    }
//Verify the if the title of id = 56979 is equal to ”Summa abduco quae blanditiis deorsum."


    @Test
    public void titleOfId() {
        response.body("title[1]", equalTo("Summa abduco quae blanditiis deorsum."));
    }

    //Check the single user_id in the Array list (4040713)
    @Test
    public void userID() {
        response.body("user_id[2]", equalTo(4040713));
    }

    //Check the multiple ids in the ArrayList (4040734, 4040719,4040713)
    @Test
    public void multipleUserID() {
        response.body("user_id", hasItems(4040734, 4040719, 4040713));

    }
    //Verify the body of userid = 4040706

    @Test
    public void bodyOfUserid() {
        response.body("user_id[4]", equalTo("Surculus viduo aduro. Ab qui umquam. Tenetur assentator summa. Sub sint et. Tribuo color conturbo. Umerus spoliatio cruentus. Tondeo armarium alias. Terebro voluptatem substantia. Cinis cribro decerno. Necessitatibus ultra animus. Triginta quo et. Eligendi adduco defendo. Chirographum calcar alveus. Eligendi conforto tum. Viriliter eos curia. Comburo universe vix. Pectus concido cupressus."));
    }



}