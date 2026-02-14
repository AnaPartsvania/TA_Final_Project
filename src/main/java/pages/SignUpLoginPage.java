package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpLoginPage extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/login";

    public SignUpLoginPage(WebDriver driver) {
        super(driver);
    }

    //Sign up
    private By newUserText = By.xpath("//h2[text()='New User Signup!']");
    private By newNameInput = By.xpath("//input[@data-qa='signup-name']");
    private By newEmailInput = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[@data-qa='signup-button']");

    //login
    private By loginText = By.xpath("//h2[text()='Login to your account']");
    private By passwordInput = By.xpath("//input[@data-qa='login-password']");
    private By emailInput = By.xpath("//input[@data-qa='login-email']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");
    private By incorrectText = By.xpath("//p[normalize-space()='Your email or password is incorrect!']");
    private By emailAlreadyExistsText = By.xpath("//p[normalize-space()='Email Address already exist!']");

    @Step("Verify 'New User Signup!' is visible")
    public String getSignupSectionTitle() {
        return driver.findElement(newUserText).getText();
    }

    @Step("Verify 'Login to your account' is visible")
    public String getLoginSectionTitle() {
        return driver.findElement(loginText).getText();
    }
    @Step("Verify error 'Your email or password is incorrect!' is visible")
    public String getIncorrectSectionTitle() {
        return driver.findElement(incorrectText).getText();
    }

    @Step("Verify error 'Email Address already exist!' is visible")
    public String getEmailAlreadyExistsSectionTitle() {
        return driver.findElement(emailAlreadyExistsText).getText();
    }

    @Step("Enter name and email address and  Click 'Signup' button")
    public void enterInitialSignup(String name, String email) {
        driver.findElement(newNameInput).sendKeys(name);
        driver.findElement(newEmailInput).sendKeys(email);
        driver.findElement(signupBtn).click();
    }

    @Step("Enter correct email address and password and click 'login' button")
    public void enterLogin(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
    }
}
