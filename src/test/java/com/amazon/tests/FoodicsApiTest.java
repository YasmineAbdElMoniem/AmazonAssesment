package com.amazon.tests;

import com.amazon.apis.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FoodicsApiTest extends BaseApi {
    public JsonPath jsonPath;
    public String token, userName, userId;

    @Test(priority = 1, description = "Verify That The User Is Logged In Successfully From Api And Get The Access Token")
    public void loginApiWithValidCredentials() {
        Response response = new LoginApi().loginApi("merchant@foodics.com", 123456, "Lyz22cfYKMetFhKQybx5HAmVimF1i0xO");
        token = new LoginApi().getAccessToken(response);
        boolean checkTokenNotEmpty = new LoginApi().checkTokenNotEmpty(response);
        assertTrue(checkTokenNotEmpty);
    }

    @Test(priority = 2, description = "Get The User Name From WhoAmI Api Response and Make Sure It Is Correct ")
    public void checkUserNameFromWhoAmIIsCorrect() {
        jsonPath = new WhoamiApi().whoamiApi(token);
        userName = new WhoamiApi().getUserName(jsonPath);
        assertEquals(userName, "Test Foodics");
    }

    @Test(priority = 3, description = "Get The User Id From WhoAmI Api Response and Make Sure It Is Correct ")
    public void checkUserIdFromWhoAmIIsCorrect() {
        jsonPath = new WhoamiApi().whoamiApi(token);
        userId = new WhoamiApi().getUserId(jsonPath);
        assertEquals(userId, "1deb74be-77fa-4eec-8980-de3116f37251");
    }

    @Test(priority = 4, description = "Verify That The User Is Created Successfully From Api Using 'Faker Library'")
    public void checkUserIsCreatedSuccessfullyFromApi() {
        jsonPath = new AddUser().addUser(token);
        String name = new AddUser().getName(jsonPath);
        assertEquals(name, "Fake Foodics User");
    }

    @Test(priority = 5, description = "Verify That The User Is Logout Successfully From Api")
    public void checkUserIsLoggedOutSuccessfully() {
        jsonPath = new LogoutApi().logoutApi(token);
        String message = new LogoutApi().getLogoutMessage(jsonPath);
        assertEquals(message, "Logged out.");
    }
}
