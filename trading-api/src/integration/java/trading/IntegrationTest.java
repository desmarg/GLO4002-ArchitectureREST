package trading;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;


public class IntegrationTest {

    private static final Logger LOGGER = Logger.getLogger(
            TradingServer.class.getName()
    );
    private static final String BASE_URI = "http://localhost";
    private static final Integer PORT = 8181;
    private static final String BASE_PATH = "/";
    private static final String BASE_URL = BASE_URI + ":" + PORT;
    private final AtomicLong accountNumber = new AtomicLong(1000);

    @BeforeClass
    public static void setupClass() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
        RestAssured.port = PORT;

        RequestSpecification httpRequest = given();
        while (true) {
            try {
                httpRequest.get("");
                break;
            } catch (Exception e) {
                LOGGER.info("Wating for " + BASE_URL + " server 1 seconds...");
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

        given().contentType("application/json")
                .body(this.getAccountWithId(1L))
                .when().post("/accounts").then()
                .statusCode(201)
                .header("Location", BASE_URL + "/accounts/TD-0001");
    }

    @Test
    public void whenPostExistingAccount_thenThrowAccountAlreadyExistsException() {
        given().contentType("application/json")
                .body(this.getAccountWithId(1L))
                .when().post("/accounts").then()
                .statusCode(400);
    }

    @Test
    public void whenPostAccountWithInvalidCredits_thenThrowInvalidCredits() {
        given().contentType("application/json")
                .body(this.getNewAccount())
                .when().post("/accounts").then()
                .statusCode(201);
    }

    private Map<String, String> getNewAccount() {
        Map<String, String> account = new HashMap<>();
        account.put("investorId", String.valueOf(this.accountNumber.incrementAndGet()));
        account.put("investorName", "Tom Drou");
        account.put("email", "pute@hot.com");
        account.put("credits", "1000");
        return account;
    }

    private Map<String, String> getAccountWithId(Long id) {
        Map<String, String> account = new HashMap<>();
        account.put("investorId", String.valueOf(id));
        account.put("investorName", "Tom Drou");
        account.put("email", "pute@hot.com");
        account.put("credits", "1000");
        return account;
    }

    private Map<String, String> getAccountWithMoney(Long initialCredits) {
        Map<String, String> account = new HashMap<>();
        account.put("investorId", String.valueOf(this.accountNumber.incrementAndGet()));
        account.put("investorName", "Tom Drou");
        account.put("email", "pute@hot.com");
        account.put("credits", String.valueOf(initialCredits));
        return account;
    }
}
