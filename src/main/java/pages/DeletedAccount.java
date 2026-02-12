package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeletedAccount extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/delete_account";

    public DeletedAccount(WebDriver driver) {
        super(driver);
    }

    private By accountDeletedText = By.xpath("//h2[@data-qa='account-deleted']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public String getPageTitle() {
        return driver.findElement(accountDeletedText).getText();
    }

    public void clickContinueBtn() {
        driver.findElement(continueBtn).click();
    }
}
