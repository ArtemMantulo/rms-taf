import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class FirstTest {

    @Test
    public void xmlUpload() {
        RestAssured.baseURI = "https://gorest.co.in/public-api/users";
        System.out.println(this.getClass().getResource("/").getPath());
        ValidatableResponse response =
                given().
                        param("id", "112").
                        when().
                        get("/").
                        then().assertThat().
                        body(matchesJsonSchemaInClasspath("schemas/response_test.json"))
                ;
        System.out.println(response.extract().response().getBody().asString());
    }
}
