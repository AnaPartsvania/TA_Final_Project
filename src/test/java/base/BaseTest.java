package base;

import api.clients.UserClient;
import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    UserClient userClient;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.initializeDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        userClient = new UserClient();
    }

    public User createAndRegisterUniqueUser() {
        User user = getUser();

        userClient.createUser(user);

        return user;
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    protected User getUser() {
        User user = new User();

        user.setName("Jane" + System.currentTimeMillis());
        user.setEmail("jane_doe" + System.currentTimeMillis() + "@gmail.com");
        user.setPassword("Pass123!");
        user.setTitle("Ms");
        user.setBirth_day("10");
        user.setBirth_month("May");
        user.setBirth_year("1990");
        user.setFirstname("Auto");
        user.setLastname("Tester");
        user.setCompany("Testing Co");
        user.setAddress1("123 5th Avenue");
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