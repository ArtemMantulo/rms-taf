package products;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProductTest {

    private final String BASE_URI = "http://k8s-kubesyst-albingre-947437f746-1930370641.us-east-1.elb.amazonaws.com/api";
    private final String BASE_PATH = "/products";

    @Test
    public void getProduct() {
        RestAssured.baseURI = BASE_URI;

        ValidatableResponse response =
                given().baseUri(BASE_URI).auth()
                        .basic("user", "agh-equ0DuDei6p")
                        .queryParam("supplierName","all")
                        .queryParam("status", "all")
                        .queryParam("nonCompletedTasks", "all")
                        .when()
                        .get(BASE_PATH)
                        .then()
                        .assertThat().statusCode(200).contentType("application/json");
//                        .body(matchesJsonSchemaInClasspath("schemas/response_products_positive.json"));


        response.extract().body().jsonPath().getJsonObject("");
//        System.out.println(response.extract().body().jsonPath().getString("products"));
    }
}
