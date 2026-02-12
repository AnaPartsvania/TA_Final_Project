package base;

import api.clients.ProductClient;
import api.clients.UserClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import models.User;
import org.testng.annotations.BeforeClass;


public class BaseApiTest {
    protected UserClient userClient;
    protected ProductClient productClient;

    @BeforeClass
    public void apiSetup() {

        userClient = new UserClient();
        productClient = new ProductClient();

        RestAssured.filters(new AllureRestAssured());
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }

    public User createAndRegisterUniqueUser() {
        User user = getUser();

        userClient.createUser(user);

        return user;
    }

    protected User getUser() {
        User user = new User();

        user.setName("Test User " + System.currentTimeMillis());
        user.setEmail("automation_" + System.currentTimeMillis() + "@gmail.com");
        user.setPassword("Pass123!");
        user.setTitle("Mr");
        user.setBirth_day("10");
        user.setBirth_month("May");
        user.setBirth_year("1990");
        user.setFirstname("Auto");
        user.setLastname("Tester");
        user.setCompany("Testing Co");
        user.setAddress1("123 Selenium Lane");
        user.setAddress2("block 8");
        user.setCountry("United States");
        user.setState("New York");
        user.setCity("NYC");
        user.setZipcode("10001");
        user.setMobile_number("5551234567");

        user.setNewsletterSubscribed(true);
        user.setSpecialOffersSubscribed(true);

        return user;

    }
}