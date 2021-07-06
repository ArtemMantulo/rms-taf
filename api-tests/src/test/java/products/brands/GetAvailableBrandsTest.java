package products.brands;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.aspectj.lang.annotation.Before;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.constants_api.ConfigApi.*;


public class GetAvailableBrandsTest {

    @Before("Get list of available product id's")
    public String getAvailableProducts() {

//        String FilterParamsAll = "all";

        RestAssured.baseURI = PRODUCTS_BASE_PATH;

        ValidatableResponse Response = given().
                baseUri(API_URL).
                auth().
                basic(BASIC_AUTH_USER, BASIC_AUTH_PASSWORD).
                queryParam(QUERY_PARAM_SUPPLIER_NAME, "all").
                queryParam(QUERY_PARAM_STATUS, "all").
                queryParam(QUERY_PARAM_NON_COMPLETED_TASKS, "all").
                when().
                get(PRODUCTS_BASE_PATH).
                then().assertThat().statusCode(200);

//        List <String> productId = Response.extract().path("products");
//        List <String> suppliers = Response.extract().path("suppliers");

        System.out.println(Response.extract().path("products").toString().indexOf(0));
//        System.out.println(productId.get(0));

//        return productId.get(0);
        return "0";
    }



    @Test
    public void getBrands() {
        RestAssured.baseURI = API_URL;

        getAvailableProducts();

    }

}
