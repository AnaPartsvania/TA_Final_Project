package pages;

import base.BasePage;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class RegistrationDetailsPage extends BasePage {
    private static final String PAGE_URL = "https://automationexercise.com/signup";

    public RegistrationDetailsPage(WebDriver driver) {
        super(driver);
    }
    public void openPage() {
        navigateToUrl(PAGE_URL);
    }

    // 1. Locators
    private By infoText = By.xpath("//h2[contains(., 'Enter Account Information')]");

    private By mrRadio = By.cssSelector("label[for='id_gender1']");
    private By mrsRadio = By.cssSelector("label[for='id_gender2']");
    private By passwordField = By.id("password");
    private By emailField = By.id("email");
    private By nameField = By.id("name");

    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By address1Field = By.id("address1");
    private By address2Field = By.id("address2");
    private By countryDropdown = By.id("country");
    private By daysDropdown = By.id("days");
    private By monthsDropdown = By.id("months");
    private By yearsDropdown = By.id("years");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipField = By.id("zipcode");
    private By mobileField = By.id("mobile_number");
    private By createAccountBtn = By.cssSelector("button[data-qa='create-account']");
    private By newsletterCheckbox = By.id("newsletter");
    private By optinCheckbox = By.id("optin");

    // 1. check title
    public String getRegPageTitle() {
        return driver.findElement(infoText).getText();
    }

    // 2. fill form
    public void fillFullRegistrationForm1(User user) {
        WebElement element = driver.findElement(mrsRadio);
        scrollToElement(element);

        if (user.getTitle() != null) {
            if (user.getTitle().equalsIgnoreCase("Mr")) {
                driver.findElement(mrRadio).click();
            } else if (user.getTitle().equalsIgnoreCase("Mrs") || user.getTitle().equalsIgnoreCase("Ms")) {
                driver.findElement(mrsRadio).click();
            }
        }
//        driver.findElement(nameField).sendKeys(user.getName());
//        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());

        Select selectDays = new Select(driver.findElement(daysDropdown));
        selectDays.selectByVisibleText(user.getBirth_day());
        Select selectMonths = new Select(driver.findElement(monthsDropdown));
        selectMonths.selectByVisibleText(user.getBirth_month());
        Select selectYears = new Select(driver.findElement(yearsDropdown));
        selectYears.selectByVisibleText(user.getBirth_year());
    }

    public void fillFullRegistrationForm2(User user) {

        driver.findElement(firstNameField).sendKeys(user.getFirstname());
        driver.findElement(lastNameField).sendKeys(user.getLastname());

        driver.findElement(companyField).sendKeys(user.getCompany());
        driver.findElement(address1Field).sendKeys(user.getAddress1());
        driver.findElement(address2Field).sendKeys(user.getAddress2());

        // for dropdowns
        Select selectCountry = new Select(driver.findElement(countryDropdown));
        selectCountry.selectByVisibleText(user.getCountry());

        driver.findElement(stateField).sendKeys(user.getState());
        driver.findElement(cityField).sendKeys(user.getCity());
        driver.findElement(zipField).sendKeys(user.getZipcode());
        driver.findElement(mobileField).sendKeys(user.getMobile_number());
    }

    public void checkerOptions(User user) {
        WebElement element = driver.findElement(newsletterCheckbox);
        scrollToElement(element);
        if (user.isNewsletterSubscribed()) {
            WebElement cb = driver.findElement(newsletterCheckbox);
            if (!cb.isSelected()) {
                cb.click();
            }
        }

        if (user.isSpecialOffersSubscribed()) {
            WebElement cb = driver.findElement(optinCheckbox);
            if (!cb.isSelected()) {
                cb.click();
            }
        }
    }
    public void clickCreateAccount() {
        WebElement element = driver.findElement(createAccountBtn);
        scrollToElement(element);
        element.click();
    }


}
