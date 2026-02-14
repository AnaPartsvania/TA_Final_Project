package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DeletedAccount extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/delete_account";

    public DeletedAccount(WebDriver driver) {
        super(driver);
    }

    private By accountDeletedText = By.xpath("//h2[@data-qa='account-deleted']");
    private By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    @Step("Verify that 'ACCOUNT DELETED!' is visible")
    public String getPageTitle() {
        return driver.findElement(accountDeletedText).getText();
    }

    @Step("Click 'continue' after deleting an account")
    public void clickContinueBtn() {
        driver.findElement(continueBtn).click();
    }
}
