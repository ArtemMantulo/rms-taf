package api;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import ui.common.Config;


import static framework.Helpers.validateResponse;
import static framework.SchemaTemplates.SCHEMA_TEMPLATE_GET_PRODUCTS;
import static io.restassured.RestAssured.given;

public class GetProductsTest {

    public static final String PRODUCTS = "/products";

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Get list of available products", groups = {"Smoke", "api"})
    public void getProductsTest() {
        RestAssured.baseURI = Config.properties.getPublicSiteUrl() + "/api";

        ValidatableResponse response =
                given().auth()
                        .basic(Config.properties.getUser(), Config.properties.getPassword())
                        .queryParam("supplierName", "all")
                        .queryParam("status", "all")
                        .queryParam("nonCompletedTasks", "all")
                        .when()
                        .get(PRODUCTS)
                        .then()
                        .assertThat().contentType(ContentType.JSON);
        validateResponse(response, SCHEMA_TEMPLATE_GET_PRODUCTS);

        int log = response.log().body().extract().statusCode();
        Allure.addAttachment("Console log: ", String.valueOf(log));
    }
}
