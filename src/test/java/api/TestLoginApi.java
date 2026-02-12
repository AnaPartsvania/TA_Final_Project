package api;

import api.endpoints.ApiEndpoints;
import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginApi extends BaseApiTest {
    //api 7 verify login with valid credentials
    @Test
    public void testLogin() {
        User user = createAndRegisterUniqueUser();

        //call client
        Response response = userClient.loginUser(user.getEmail(), user.getPassword());
        int internalCode = response.jsonPath().getInt("responseCode");
        response.prettyPrint();

        //assert
        Assert.assertEquals(internalCode, 200, "API failed to login!");
        Assert.assertEquals(response.jsonPath().getString("message"), "User exists!");
    }

    //api 8 verify login with email missing
    @Test
    public void testLoginNoEmail() {
        String loginPassword = "Pass321";

        //call client
        Response response = userClient.loginUser(null, loginPassword);
        response.prettyPrint();

        //assert
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 400);
        Assert.assertEquals(response.jsonPath().getString("message"), "Bad request, email or password parameter is missing in POST request.");
    }

    //api 9 Delete verify login
    @Test
    public void verifyDeleteToLoginNotSupported() {
        Response response = RestAssured.given()
                .delete(ApiEndpoints.BASE_URL + ApiEndpoints.VERIFY_LOGIN);

        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 405);
        Assert.assertEquals(response.jsonPath().getString("message"), "This request method is not supported.");
    }

    //api 10 verify login with wrong credentials
    @Test
    public void testLoginWrongDetails() {
        String loginEmail = "janeDoo@gmail.com";
        String loginPassword = "Pass3";

        Response response = userClient.loginUser(loginEmail, loginPassword);
        response.prettyPrint();

        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 404);
        Assert.assertEquals(response.jsonPath().getString("message"), "User not found!");
    }
}
