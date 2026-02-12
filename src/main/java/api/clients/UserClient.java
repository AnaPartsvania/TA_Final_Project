package api.clients;

import api.endpoints.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import java.util.Map;
import java.util.HashMap;

public class UserClient {

    //Register and update/delete

    public Response createUser(User user) {
        return RestAssured
                .given()
                .formParams(mapUserData(user))
                .post(ApiEndpoints.BASE_URL + ApiEndpoints.CREATE_ACCOUNT);
    }

    public Response getUserDetails(String email) {
        return RestAssured
                .given()
                .queryParam("email", email)
                .get(ApiEndpoints.BASE_URL + ApiEndpoints.GET_USER_DETAIL_BY_EMAIL);
    }

    public Response updateUser(User user) {
        return RestAssured.given()
                .formParams(mapUserData(user))
                .put(ApiEndpoints.BASE_URL + ApiEndpoints.UPDATE_ACCOUNT);
    }

    public Response deleteUser(String email, String password) {
        return RestAssured.given()
                .formParam("email", email)
                .formParam("password", password)
                .delete(ApiEndpoints.BASE_URL + ApiEndpoints.DELETE_ACCOUNT);
    }

    //Login
    public Response loginUser(String email, String password) {
        Map<String, String> loginData = new HashMap<>();
        if (email != null) loginData.put("email", email);
        if (password != null) loginData.put("password", password);

        return RestAssured.given()
                .formParams(loginData)
                .post(ApiEndpoints.BASE_URL + ApiEndpoints.VERIFY_LOGIN);
    }
    private Map<String, String> mapUserData(User user) {
        Map<String, String> data = new HashMap<>();
        data.put("name", user.getName());
        data.put("email", user.getEmail());
        data.put("password", user.getPassword());
        data.put("title", user.getTitle());
        data.put("birthday", user.getBirth_day());
        data.put("birth month", user.getBirth_month());
        data.put("birth year", user.getBirth_year());
        data.put("firstname", user.getFirstname());
        data.put("lastname", user.getLastname());
        data.put("company", user.getCompany());
        data.put("address1", user.getAddress1());
        data.put("address2", user.getAddress2());
        data.put("country", user.getCountry());
        data.put("zipcode", user.getZipcode());
        data.put("state", user.getState());
        data.put("city", user.getCity());
        data.put("mobile_number", user.getMobile_number());
        return data;
    }
}
