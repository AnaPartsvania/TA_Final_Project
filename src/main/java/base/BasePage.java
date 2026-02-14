package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    private static final int DEFAULT_TIMEOUT = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.actions = new Actions(driver);
        handleVignette();
    }

    public void handleVignette() {
        if (driver.getCurrentUrl().contains("google_vignette")) {
            try {

                driver.switchTo().frame("ad_iframe");
                WebElement dismissButton = driver.findElement(By.id("dismiss-button"));
                dismissButton.click();
                driver.switchTo().defaultContent();
            } catch (Exception e) {

                driver.navigate().refresh();
            }
        }
    }

    protected void navigateToUrl(String url) {
        driver.get(url);
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForVisibilityOfElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void moveToElement(WebElement element, long pauseMillis) {
        actions.moveToElement(element).pause(pauseMillis).perform();
    }

}
