package ui;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import base.BaseTest;

public class TestRegisterUser extends BaseTest{
    private HomePage homePage;
    private SignUpLoginPage loginPage;
    private RegistrationDetailsPage regPage;
    private AccountCreated accountCreated;
    private DeletedAccount deletedAccount;

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(driver);
        loginPage = new SignUpLoginPage(driver);
        regPage = new RegistrationDetailsPage(driver);
        accountCreated = new AccountCreated(driver);
        deletedAccount = new DeletedAccount(driver);
    }



    @Test
    @Description("Test case 1: Create an account for a new user")

    public void testingRegisterUserFlow() {
        User user = getUser();
        homePage.openPage();

        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");
        homePage.clickSignupLogin();

        Assert.assertEquals(loginPage.getSignupSectionTitle(), "New User Signup!");
        loginPage.enterInitialSignup(user.getName(),user.getEmail());

        Assert.assertEquals(regPage.getRegPageTitle(), "ENTER ACCOUNT INFORMATION");
        regPage.fillFullRegistrationForm1(user);
        regPage.checkerOptions(user);
        regPage.fillFullRegistrationForm2(user);
        regPage.clickCreateAccount();

        Assert.assertEquals(accountCreated.getPageTitle(), "ACCOUNT CREATED!");
        accountCreated.clickContinueBtn();

        String expectedText = "Logged in as " + user.getName();
        Assert.assertEquals(homePage.getLoggedInUserText(), expectedText, "The 'Logged in as' text is incorrect!");
        homePage.clickDeleteBtn();

        Assert.assertEquals(deletedAccount.getPageTitle(), "ACCOUNT DELETED!");
        deletedAccount.clickContinueBtn();
    }


    @Test
    @Description("Test case 5: register user with existing email")

    public void testingRegisterUserWithWrongEmailFlow() {
        User user = new User();

        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");
        homePage.clickSignupLogin();

        user.setName("jane9");
        user.setEmail("janeDoe777@gmail.com");


        Assert.assertEquals(loginPage.getSignupSectionTitle(), "New User Signup!");
        loginPage.enterInitialSignup(user.getName(),user.getEmail());

        Assert.assertEquals(loginPage.getEmailAlreadyExistsSectionTitle(), "Email Address already exist!");

    }
}
