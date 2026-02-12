package api.endpoints;

public class ApiEndpoints {
    // base URL
    public static final String BASE_URL = "https://automationexercise.com";

    // products endpoints
    public static final String PRODUCTS_LIST = "/api/productsList";
    public static final String SEARCH_PRODUCT = "/api/searchProduct";

    // brands endpoints
    public static final String BRANDS_LIST = "/api/brandsList";

    // user authentication endpoints
    public static final String VERIFY_LOGIN = "/api/verifyLogin";
    public static final String CREATE_ACCOUNT = "/api/createAccount";
    public static final String DELETE_ACCOUNT = "/api/deleteAccount";
    public static final String UPDATE_ACCOUNT = "/api/updateAccount";
    public static final String GET_USER_DETAIL_BY_EMAIL = "/api/getUserDetailByEmail";

    private ApiEndpoints() {

    }
}
