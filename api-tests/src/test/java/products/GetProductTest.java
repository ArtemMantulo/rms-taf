package products;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProductTest {

    private final String BASE_URI = "http://k8s-kubesyst-albingre-947437f746-1930370641.us-east-1.elb.amazonaws.com/api";
    private final String BASE_PATH = "/products";
    private final String AUTH_USER = "user";
    private final String AUTH_PASSWORD = "agh-equ0DuDei6p";

    @Test(priority = 0, description = "Get product")
    @Description("Get product")
    public void getProduct() {
        RestAssured.baseURI = BASE_URI;

        ValidatableResponse response =
                given().baseUri(BASE_URI).auth()
                        .basic(AUTH_USER, AUTH_PASSWORD)
                        .queryParam("supplierName","all")
                        .queryParam("status", "all")
                        .queryParam("nonCompletedTasks", "all")
                        .when()
                        .get(BASE_PATH)
                        .then()
                        .assertThat().statusCode(200).contentType("application/json");

        int log  = response.log().body().extract().statusCode();
        Allure.addAttachment("Console log: ", String.valueOf(log));
    }
}
