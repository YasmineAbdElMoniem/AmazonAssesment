package com.amazon.apis;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LogoutApi extends BaseApi {
    @Step("LogOut")
    public JsonPath logoutApi(String token) {

        setUpRequestSpecification();
        setUpResponseSpecification();

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpec).log().all()
                .when()
                .body("{}")
                .post(apiPath.LOGOUT.getValue())
                .then().spec(responseSpec).log().all()
                .extract().jsonPath();
    }

    @Step("Get Logout Message")
    public String getLogoutMessage(JsonPath jsonPath) {
        String message = jsonPath.getString("message");
        System.out.println("The Message is " + message);
        return message;
    }
}
