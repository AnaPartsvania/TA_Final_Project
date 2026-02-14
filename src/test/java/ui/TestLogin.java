package ui;

import api.clients.UserClient;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DeletedAccount;
import pages.HomePage;
import pages.SignUpLoginPage;

public class TestLogin extends BaseTest{
    private HomePage homePage;
    private SignUpLoginPage loginPage;
    private DeletedAccount deletedAccount;
    private UserClient userClient;

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(driver);
        loginPage = new SignUpLoginPage(driver);
        deletedAccount = new DeletedAccount(driver);
        userClient = new UserClient();
    }


    @Test
    @Description("Test case 2: Login with correct credentials(email, password)")

    public void testLoginFlow() {

        homePage.openPage();

        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");
        homePage.clickSignupLogin();

        User user = createAndRegisterUniqueUser();

        Assert.assertEquals(loginPage.getLoginSectionTitle(), "Login to your account");
        loginPage.enterLogin(user.getEmail(), user.getPassword());

        //get name from server
        Response response = userClient.getUserDetails(user.getEmail());
        String nameFromServer = response.jsonPath().getString("user.name");
        user.setName(nameFromServer);

        String expectedText = "Logged in as " + user.getName();
        Assert.assertEquals(homePage.getLoggedInUserText(), expectedText, "The 'Logged in as' text is incorrect!");
        homePage.clickDeleteBtn();

        Assert.assertEquals(deletedAccount.getPageTitle(), "ACCOUNT DELETED!");
    }



    @Test
    @Description("Test case 4: Verify log out functionality")

    public void testLogoutFlow() {

        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");
        homePage.clickSignupLogin();

        User user = createAndRegisterUniqueUser();

        Assert.assertEquals(loginPage.getLoginSectionTitle(), "Login to your account");
        loginPage.enterLogin(user.getEmail(), user.getPassword());

        //get name from server
        Response response = userClient.getUserDetails(user.getEmail());
        String nameFromServer = response.jsonPath().getString("user.name");
        user.setName(nameFromServer);

        String expectedText = "Logged in as " + user.getName();
        Assert.assertEquals(homePage.getLoggedInUserText(), expectedText, "The 'Logged in as' text is incorrect!");

        homePage.clickLogOutBtn();
        Assert.assertEquals(loginPage.getLoginSectionTitle(), "Login to your account");
    }



    @Test
    @Description("Test case 3: Login with wrong credentials")

    public void testLoginWrongFlow() {
        User user = new User();

        homePage.openPage();

        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");
        homePage.clickSignupLogin();

        user.setEmail("janeWrong@gmail.com");
        user.setPassword("Pass123");

        Assert.assertEquals(loginPage.getLoginSectionTitle(), "Login to your account");
        loginPage.enterLogin(user.getEmail(), user.getPassword());

        Assert.assertEquals(loginPage.getIncorrectSectionTitle(), "Your email or password is incorrect!");

    }

}
