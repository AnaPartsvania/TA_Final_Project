package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCases extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/test_cases";

    public TestCases(WebDriver driver) {
        super(driver);
    }

    private By testCasesText = By.xpath("//h2[contains(., 'Test Cases')]");

    @Step("Verify user is navigated to test cases page successfully")
    public String getTestCasesText() {
        return driver.findElement(testCasesText).getText();
    }
}
