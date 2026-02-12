package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Products extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/products";

    private By pageText = By.xpath("//h2[text()='All Products']");
    private By productItems = By.className("col-sm-4");
    private By continueShoppingBtn = By.xpath("//button[text()='Continue Shopping']");
    private By viewCart = By.cssSelector("a[href='/view_cart']");

    public Products(WebDriver driver) {
        super(driver);
    }

    public String getPageText() {
        return driver.findElement(pageText).getText();
    }

    public boolean areProductsVisible() {
        return driver.findElements(productItems).size() > 0;
    }

    public void clickViewProductByIndex(int index) {
        // Finds all "View Product" links and clicks the one at the given index
        driver.findElements(By.cssSelector(".col-sm-4 .choose a")).get(index).click();
    }


    public void addProductToCart(int index) {
        WebElement productCard = driver.findElements(By.className("single-products")).get(index);
        WebElement addToCartBtn = driver.findElements(By.xpath("//div[@class='overlay-content']//a[text()='Add to cart']")).get(index);

        scrollToElement(productCard);
        moveToElement(productCard,500);
        waitForElementToBeClickable(addToCartBtn).click();
    }

    public void clickContinueShopping() {
        waitForVisibilityOfElement(continueShoppingBtn).click();
    }

    public void clickViewCart() {
        driver.findElement(viewCart).click();
    }
}
