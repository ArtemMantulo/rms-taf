package api;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static api.Config.*;
import static io.restassured.RestAssured.given;

public class GetProductsTest {

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Get list of available products", groups = "Smoke")
    public void getProductsTest() {
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
