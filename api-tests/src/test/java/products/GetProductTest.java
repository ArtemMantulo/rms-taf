package products;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetProductTest {

    private final String BASE_URI = "http://k8s-kubesyst-albingre-20a87abfe5-2022848426.us-east-1.elb.amazonaws.com/api";
    private final String PRODUCTS_URI = "/products";

    @Test
    public void getProduct() {
        RestAssured.baseURI = BASE_URI;

        ValidatableResponse response =
                given().baseUri(BASE_URI).
                        when().
                        get(PRODUCTS_URI).
                        then().
                        assertThat().
                        body(matchesJsonSchemaInClasspath("schemas/response_products_positive.json"));

    }
}
