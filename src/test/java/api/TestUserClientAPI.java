package api;

import api.endpoints.ApiEndpoints;
import base.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUserClientAPI extends BaseApiTest {

    //api 11 create account
    @Test
    public void testRegistration() {
        User user = getUser();

        // call client
        Response response = userClient.createUser(user);
        int internalCode = response.jsonPath().getInt("responseCode");
        response.prettyPrint();

        // assert
        Assert.assertEquals(internalCode, 201, "API failed to create user!");
        Assert.assertEquals(response.jsonPath().getString("message"), "User created!");

    }

    //api 14 get email
    @Test
    public void verifyGetUserDetails() {
        String targetEmail = "janeDoe777@gmail.com";

        // call client
        Response response = userClient.getUserDetails(targetEmail);
        response.prettyPrint();

        // assert
        int internalCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(internalCode, 200);

        String actualName = response.jsonPath().getString("user.name");
        Assert.assertNotNull(actualName, "User name should not be null!");
    }

    //api 13 update account
    @Test
    public void updateAccount() {
        // create user first
//        User user = getUser();
//        userClient.createUser(user);
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

    //api 12 delete account
    @Test
    public void verifyDeleteUser() {
//        String targetEmail = "janeDoe323@gmail.com";
//        String targetPassword = "Pass321";
        User user = createAndRegisterUniqueUser();

        // call client
        Response response = userClient.deleteUser(user.getEmail(), user.getPassword());
        response.prettyPrint();

        //assert
        int internalCode = response.jsonPath().getInt("responseCode");
        Assert.assertEquals(internalCode, 200);

    }

//    private static User getUser() {
//        User user = new User();
//        String timestamp = String.valueOf(System.currentTimeMillis());
//
//
//        user.setName("Jane" + timestamp);
//        user.setEmail("janeDoe" + timestamp + "@gmail.com");
//        user.setPassword("Pass" + timestamp);
//        user.setTitle("Ms");
//        user.setBirth_day("12");
//        user.setBirth_month("May");
//        user.setBirth_year("1990");
//        user.setFirstname("Jane");
//        user.setLastname("Doe");
//        user.setCompany("Testing Co");
//        user.setAddress1("123 Selenium Lane");
//        user.setAddress2("block 8");
//        user.setCountry("United States");
//        user.setState("New York");
//        user.setCity("NYC");
//        user.setZipcode("10001");
//        user.setMobile_number("5551234567");
//        return user;
//    }
}
