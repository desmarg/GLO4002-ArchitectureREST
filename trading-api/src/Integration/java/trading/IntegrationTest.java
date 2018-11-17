package trading;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;


public class IntegrationTest {

    private static final Logger LOGGER = Logger.getLogger(
            TradingServer.class.getName()
    );

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/";
        RestAssured.port = 8181;

        RequestSpecification httpRequest = given();
        while (true) {
            try {
                httpRequest.get("");
                break;
            } catch (Exception e) {
                LOGGER.info("Wating for " + RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath + " server 1 seconds...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    @Test
    public void whenPostAccountValid_thenReturnCreated() {
        Map<String, String> account = new HashMap<>();
        account.put("investorId", "1");
        account.put("investorName", "Tom Caca");
        account.put("email", "pute@hot.com");
        account.put("credits", "1000");

        given().contentType("application/json")
                .body(account)
                .when().post("/accounts").then()
                .statusCode(201);

    }
}
