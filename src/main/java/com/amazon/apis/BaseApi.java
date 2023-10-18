package com.amazon.apis;

import com.amazon.utils.Config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.amazon.utils.JsonFileManager.readJSONFile;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BaseApi {
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    static String baseAPIPath = Config.getBaseApiPath();

    public static void setUpRequestSpecification() {
        requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public static void setUpResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(anyOf(is(200), is(201))).
                expectHeader("Content-Type", "application/json").
                expectContentType(ContentType.JSON).
                build();
    }

    public static String getJsonFilePath(String filePath) {
        return String.valueOf(readJSONFile("src/test/java/com/amazon/resources/ApiRequests/" + filePath));
    }

    public int checkApiStatusCode(Response response) {
        return response.statusCode();
    }

    public enum apiPath {
        WHOMI(baseAPIPath + "/whoami"),
        LOGIN(baseAPIPath + "/login"),
        LOGOUT(baseAPIPath + "/logout"),
        USERS(baseAPIPath + "/users");

        private final String value;

        apiPath(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;

        }
    }

}
