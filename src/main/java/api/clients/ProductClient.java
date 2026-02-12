package api.clients;

import api.endpoints.ApiEndpoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductClient {
    //Products
    public Response getAllProducts() {
        return RestAssured.given()
                .get(ApiEndpoints.BASE_URL + ApiEndpoints.PRODUCTS_LIST);
    }

    public Response getAllBrands() {
        return RestAssured.given()
                .get(ApiEndpoints.BASE_URL + ApiEndpoints.BRANDS_LIST);
    }

    public Response searchProduct(String productName) {
        return RestAssured
                .given()
                .formParam("search_product", productName)
                .when()
                .post(ApiEndpoints.BASE_URL + ApiEndpoints.SEARCH_PRODUCT);
    }
}
