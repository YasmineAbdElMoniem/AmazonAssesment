package com.amazon.apis;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddUser extends BaseApi {

    @Step("Add New User")
    public JsonPath addUser(String token) {
        //generate Random Emails From Java Faker
        String emailAddress = new Faker().internet().emailAddress();

        setUpRequestSpecification();
        setUpResponseSpecification();

        // Replace placeholders in the JSON body with actual values
        String requestBody = getJsonFilePath("AddUser.json")
                .replace("{{email}}", emailAddress);

        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpec).log().all()
                .when()
                .body(requestBody)
                .post(apiPath.USERS.getValue())
                .then().spec(responseSpec).log().all().
                extract().jsonPath();
    }

    @Step("Get The UserName Of The Newly Created User")
    public String getName(JsonPath jsonPath) {
        String message = jsonPath.getString("data.name");
        System.out.println("The Name is " + message);
        return message;
    }
}
