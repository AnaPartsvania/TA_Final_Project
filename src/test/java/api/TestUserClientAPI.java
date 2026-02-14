package api;

import api.endpoints.ApiEndpoints;
import base.BaseApiTest;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUserClientAPI extends BaseApiTest {


    @Test
    @Description("API 11: create account")
    public void testRegistration() {
        User user = getUser();

        Response response = userClient.createUser(user);
        int internalCode = response.jsonPath().getInt("responseCode");

        Assert.assertEquals(internalCode, 201, "API failed to create user!");
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!");

    }


    @Test
    @Description("API 14: get user account details by email")
    public void verifyGetUserDetails() {
        String targetEmail = "janeDoe777@gmail.com";

        Response response = userClient.getUserDetails(targetEmail);

        int internalCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(internalCode, 200);

        String actualName = response.jsonPath().getString("user.name");
        Assert.assertNotNull(actualName, "User name should not be null!");
    }


    @Test
    @Description("API 13: update user account")
    public void updateAccount() {
        // create user first
        User user = createAndRegisterUniqueUser();

        // update fields
        String updatedName = "Jane9";
        String updatedCity = "New York City";

        user.setName(updatedName);
        user.setCity(updatedCity);

        // call api
        Response updateResponse = userClient.updateUser(user);

        // assert
        int internalCode = updateResponse.jsonPath().getInt("responseCode");
        Assert.assertEquals(internalCode, 200, "Update API failed!");

        // verifying
        Response getResponse = userClient.getUserDetails(user.getEmail());
        String actualName = getResponse.jsonPath().getString("user.name");
        String actualCity = getResponse.jsonPath().getString("user.city");

        Assert.assertEquals(actualName, updatedName, "Name was not updated in database!");
        Assert.assertEquals(actualCity, updatedCity, "City was not updated in database!");
    }


    @Test
    @Description("API 12: delete account")
    public void verifyDeleteUser() {
        User user = createAndRegisterUniqueUser();

        Response response = userClient.deleteUser(user.getEmail(), user.getPassword());

        int internalCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(internalCode, 200);

    }
}
