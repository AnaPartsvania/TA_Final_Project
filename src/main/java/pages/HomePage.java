package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/";

    private By homeLogo = By.xpath("//img[@alt= 'Website for automation practice']"); // or any unique home element
    private By footerWidget = By.cssSelector(".footer-widget");
    private By subscribeEmail = By.id("susbscribe_email");
    private By successMsg = By.cssSelector(".alert-success.alert");

    //Buttons
    private By signupLoginBtn = By.xpath("//a[contains(text(), 'Signup / Login')]");
    private By loggedInAsText = By.xpath("//i[@class='fa fa-user']/parent::a");
    private By deleteBtn = By.xpath("//i[@class='fa fa-trash-o']/parent::a");
    private By logOutBtn = By.xpath("//i[@class='fa fa-lock']/parent::a");
    private By cartBtn = By.xpath("//i[@class='fa fa-shopping-cart']/parent::a");
    private By productsBtn = By.xpath("//i[@class='material-icons card_travel']/parent::a");
    private By testCaseBtn = By.xpath("//i[@class='fa fa-list']/parent::a");
    private By arrowBtn = By.xpath("//i[@class='fa fa-arrow-circle-o-right']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        navigateToUrl(PAGE_URL);
    }

    @Step("Verify that home page is visible successfully")
    public boolean isHomePageVisible() {
        return driver.findElement(homeLogo).isDisplayed();
    }

    @Step("Verify 'Logged in' text is visible")
    public String getLoggedInUserText() {
        return driver.findElement(loggedInAsText).getText();
    }

    //Click buttons
    public void clickSignupLogin() {
        driver.findElement(signupLoginBtn).click();
    }

    public void clickCartBtn() {
        driver.findElement(cartBtn).click();
    }
    public void clickProductsBtn() {
        driver.findElement(productsBtn).click();
    }
    public void clickDeleteBtn() {
        driver.findElement(deleteBtn).click();
    }

    public void clickLogOutBtn() {
        driver.findElement(logOutBtn).click();
    }

    public void clickTestCaseBtn() {
        driver.findElement(testCaseBtn).click();
    }

    @Step("Enter email address in input and click arrow button")
    public void subscribeEmail(String email) {
        WebElement footer = driver.findElement(footerWidget);
        scrollToElement(footer);

        driver.findElement(subscribeEmail).sendKeys(email);
        driver.findElement(arrowBtn).click();
    }

    @Step("Verify success message 'You have been successfully subscribed!' is visible")
    public boolean successMsgIsDisplayed() {
        WebElement success = waitForVisibilityOfElement(successMsg);
        return success.isDisplayed();
    }
}
