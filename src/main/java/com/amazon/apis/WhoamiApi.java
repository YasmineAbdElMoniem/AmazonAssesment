package com.amazon.apis;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class WhoamiApi extends BaseApi {
    @Step("Get The User Information")
    public JsonPath whoamiApi(String accessToken) {

        setUpRequestSpecification();
        setUpResponseSpecification();

        return RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .spec(requestSpec).log().all()
                .when()
                .body("")
                .get(apiPath.WHOMI.getValue())
                .then().spec(responseSpec).log().all()
                .extract().jsonPath();
    }

    @Step("Get The User Id")
    public String getUserId(JsonPath jsonPath) {
        String userId = jsonPath.getString("user.id");
        System.out.println("The User Id is " + userId);
        return userId;
    }

    @Step("Get The User Name")
    public String getUserName(JsonPath jsonPath) {
        String userName = jsonPath.getString("user.name");
        System.out.println("The User Name is " + userName);
        return userName;
    }

}
