package ui;

import base.BaseTest;
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

    //Test case 8 verify all products and detail page
    @Test
    public void verifyAllProducts() {
        homePage.openPage();
        Assert.assertTrue(homePage.isHomePageVisible(), "Homepage is not visible!");

        homePage.clickProductsBtn();
        Assert.assertEquals(products.getPageText(), "ALL PRODUCTS");

        Assert.assertTrue(products.areProductsVisible(), "product list is not visible!");

        products.clickViewProductByIndex(0);

        //verify product detail page

    }

    //Test case 12 add products to the card
    @Test
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
