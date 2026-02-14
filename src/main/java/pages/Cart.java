package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/view_cart";

    public Cart(WebDriver driver) {
        super(driver);
    }

    private By footerWidget = By.cssSelector(".footer-widget");
    private By subscribeEmail = By.id("susbscribe_email");
    private By arrowBtn = By.xpath("//i[@class='fa fa-arrow-circle-o-right']");
    private By successMsg = By.cssSelector(".alert-success.alert");

    @Step("Enter email address in input and click arrow button\n")
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
