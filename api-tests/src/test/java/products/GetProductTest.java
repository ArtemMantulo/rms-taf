package products;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProductTest {

    @Test
    public void getProduct() {
        RestAssured.baseURI =
                "http://k8s-kubesyst-albingre-20a87abfe5-2022848426.us-east-1.elb.amazonaws.com/api/products";
//                System.out.println(this.getClass().getResource("/").getPath());

        ValidatableResponse response =
                given().
                        when().
                        get("/").
                        then().
                        assertThat().statusCode(200);
//                        body(matchesJsonSchemaInClasspath("schemas/response_products_positive.json"));
        System.out.println(response.extract().response().getBody().asString());

    }
}
