package api;

import api.endpoints.ApiEndpoints;
import base.BaseApiTest;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginApi extends BaseApiTest {

    @Test
    @Description("API 7: verify login with valid credentials")
    public void testLogin() {
        User user = createAndRegisterUniqueUser();

        Response response = userClient.loginUser(user.getEmail(), user.getPassword());
        int internalCode = response.jsonPath().getInt("responseCode");

        Assert.assertEquals(internalCode, 200, "API failed to login!");
        Assert.assertEquals(response.jsonPath().getString("message"), "User exists!");
    }


    @Test
    @Description("API 8: verify login with email missing")
    public void testLoginNoEmail() {
        String loginPassword = "Pass321";

        //call client
        Response response = userClient.loginUser(null, loginPassword);

        //assert
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 400);
        Assert.assertEquals(response.jsonPath().getString("message"), "Bad request, email or password parameter is missing in POST request.");
    }


    @Test
    @Description("API 9: verify that delete request is not supported")
    public void verifyDeleteToLoginNotSupported() {
        Response response = RestAssured.given()
                .delete(ApiEndpoints.BASE_URL + ApiEndpoints.VERIFY_LOGIN);

        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 405);
        Assert.assertEquals(response.jsonPath().getString("message"), "This request method is not supported.");
    }


    @Test
    @Description("API 10: verify login with wrong credentials")
    public void testLoginWrongDetails() {
        String loginEmail = "janeDoo@gmail.com";
        String loginPassword = "Pass3";

        Response response = userClient.loginUser(loginEmail, loginPassword);

        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "User not found!");
    }
}
