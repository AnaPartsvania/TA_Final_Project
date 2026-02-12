package ui;

import base.BaseTest;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart;
import pages.HomePage;
import pages.TestCases;

public class TestPages extends BaseTest{
    private HomePage homePage;
    private TestCases testCases;
    private Cart cart;
    User user;

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(driver);
        testCases = new TestCases(driver);
        cart = new Cart(driver);
        user = new User();
    }

    //Test case 7 verify test cases page
    @Test
    public void verifyTestHomePage() {
        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");

        homePage.clickTestCaseBtn();
        Assert.assertEquals(testCases.getTestCasesText(), "TEST CASES");
    }

    //Test case 10 verify subscription
    @Test
    public void verifySubscribeEmail() {
        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");

        user.setSubscribeEmail("test@test.com");
        homePage.subscribeEmail(user.getSubscribeEmail());
        Assert.assertTrue(homePage.successMsgIsDisplayed(), "Subscribe email is not displayed!");
    }

    //Test case 11 verify subscription on Cart page
    @Test
    public void verifySubscribeEmailCart() {
        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");
        homePage.clickCartBtn();

        user.setSubscribeEmail("test@test.com");
        cart.subscribeEmail(user.getSubscribeEmail());
        Assert.assertTrue(cart.successMsgIsDisplayed(), "Subscribe email is not displayed!");
    }
 }
