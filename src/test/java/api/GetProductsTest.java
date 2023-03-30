package api;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import ui.base.BaseTest;

import static enums.WebEnvProperties.*;
import static framework.Helpers.validateResponse;
import static framework.SchemaTemplates.SCHEMA_TEMPLATE_GET_PRODUCTS;
import static io.restassured.RestAssured.given;

public class GetProductsTest extends BaseTest {
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Get list of available products", groups = {"Smoke", "api"})
    public void executeMytest() {
      System.out.println("My test");
    }
}
