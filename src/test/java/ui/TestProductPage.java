package ui;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Products;

public class TestProductPage extends BaseTest{
    private HomePage homePage;
    Products products;

    @BeforeMethod
    public void initPage() {
        homePage = new HomePage(driver);
        products = new Products(driver);
    }


    @Test
    @Description("Test case 8: Verify that all products are displayed on the products page")

    public void verifyAllProducts() {
        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");

        homePage.clickProductsBtn();
        Assert.assertEquals(products.getPageText(), "ALL PRODUCTS");

        Assert.assertTrue(products.areProductsVisible(), "product list is not visible!");

        products.clickViewProductByIndex(0);

        //verify product detail page

    }


    @Test
    @Description(" Test case 12: Verify that user can add a product to the cart, continue shopping and then check the cart")

    public void verifyAddProduct() {
        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");

        homePage.clickProductsBtn();
        Assert.assertEquals(products.getPageText(), "ALL PRODUCTS");

        Assert.assertTrue(products.areProductsVisible(), "product list is not visible!");

        products.addProductToCart(1);
        products.clickContinueShopping();
        products.addProductToCart(2);
        products.clickViewCart();
    }
}
