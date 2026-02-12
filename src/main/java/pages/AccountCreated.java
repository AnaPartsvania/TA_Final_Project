package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreated extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/account_created";

    public AccountCreated(WebDriver driver) {
        super(driver);
    }

    private By accountCreatedText = By.xpath("//h2[@data-qa='account-created']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public String getPageTitle() {
        return driver.findElement(accountCreatedText).getText();
    }

    public void clickContinueBtn() {
        driver.findElement(continueBtn).click();
    }
}
