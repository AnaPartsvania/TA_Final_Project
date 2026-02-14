package api;

import base.BaseApiTest;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestProductsApi extends BaseApiTest {


    @Test
    @Description("API 1: get all products")
    public void getAllProducts() {
        Response response = productClient.getAllProducts();
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 200);

        List<Map<String, Object>> products = response.jsonPath().getList("products");
        Assert.assertFalse(products.isEmpty(), "Product list should not be empty!");
    }


    @Test
    @Description("API 3: get all brands")
    public void getAllBrands() {
        Response response = productClient.getAllBrands();

        response.then().assertThat().statusCode(200);

        List<Map<String, Object>> brands = response.jsonPath().getList("brands");
        Assert.assertFalse(brands.isEmpty(), "Brand list should not be empty!");
    }


    @Test
    @Description("API 5: search a product")
    public void searchProducts() {
        String product = "top";

        Response response = productClient.searchProduct(product);
        response.then().assertThat().statusCode(200);
    }
}
