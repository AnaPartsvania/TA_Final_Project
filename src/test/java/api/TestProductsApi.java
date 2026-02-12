package api;

import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestProductsApi extends BaseApiTest {

    //api 1 get all products
    @Test
    public void getAllProducts() {
        Response response = productClient.getAllProducts();
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().getInt("responseCode"), 200);

        List<Map<String, Object>> products = response.jsonPath().getList("products");
        Assert.assertFalse(products.isEmpty(), "Product list should not be empty!");
    }

    //api 3 get all brands
    @Test
    public void getAllBrands() {
        Response response = productClient.getAllBrands();
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        List<Map<String, Object>> brands = response.jsonPath().getList("brands");
        Assert.assertFalse(brands.isEmpty(), "Brand list should not be empty!");
    }

    //api 5 search a product
    @Test
    public void searchProducts() {
        String product = "top";

        Response response = productClient.searchProduct(product);
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
}
