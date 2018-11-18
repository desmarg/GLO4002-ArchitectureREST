package trading;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private static final Logger LOGGER = Logger.getLogger(TradingServer.class.getName());
    private static final String BASE_URI = "http://localhost";
    private static final Integer PORT = 8181;
    private static final String BASE_PATH = "/";
    private static final String BASE_URL = BASE_URI + ":" + PORT;
    private final AtomicLong accountNumber = new AtomicLong(1000);
    private final long AN_ACCOUNT_ID = 1l;
    private final long EXISTING_ACCOUNT_ID = 1l;
    private final String CREDITS_AMOUNT = "1000";
    private final String NAME = "Tom Drou";
    private final String EMAIL = "bob@hot.com";
    private final String ACCOUNT_NUMBER = "TD-0001";
    private Map<String, String> ACCOUNT;

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

        given()
                .contentType("application/json")
                .body(this.getAccountWithId(this.AN_ACCOUNT_ID))
                .when()
                .post("/accounts")
                .then()
                .statusCode(201)
                .header("Location", BASE_URL + "/accounts/" + this.ACCOUNT_NUMBER);
    }

    @Test
    public void whenPostExistingAccount_thenThrowAccountAlreadyExistsException() {
        given()
                .contentType("application/json")
                .body(this.getAccountWithId(this.EXISTING_ACCOUNT_ID))
                .when()
                .post("/accounts")
                .then()
                .statusCode(400)
                .body("error", Matchers.equalTo("ACCOUNT_ALREADY_OPEN"))
                .body("description", Matchers.equalTo("account already open for investor " + this.EXISTING_ACCOUNT_ID));

    }

    @Test
    public void whenPostAccountWithInvalidCredits_thenThrowInvalidCredits() {
        given()
                .contentType("application/json")
                .body(this.getNewAccountWithCredits(0l))
                .when()
                .post("/accounts")
                .then()
                .statusCode(400)
                .body("error", Matchers.equalTo("INVALID_AMOUNT"))
                .body("description", Matchers.equalTo("credit amount cannot be lower than or equal to zero"));
    }

    @Test
    public void whenGetExistingAccount_thenReturnAccount() {
        JsonPath account =

                given()
                        .body(this.getAccountWithId(this.EXISTING_ACCOUNT_ID))
                        .pathParam("accountNumber", this.ACCOUNT_NUMBER)
                        .contentType("application/json")
                        .when()
                        .get("/accounts/{accountNumber}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath();

        assertEquals(account.get("accountNumber"), "TD-0001");
    }

    @Test
    public void givenValidAccount_whenPostValidBuyTransaction_thenReturnCreated() {
        Map<String, String> transaction = this.getNewAccount();

        given()
                .contentType("application/json")
                .body(account)
                .then()
                .post("/accounts/" + this.ACCOUNT_NUMBER + "/transactions")
                .statusCode(201)
                .header("Location", BASE_URL + "/accounts/" + account.get(this.ACCOUNT_NUMBER));
    }

    private Map<String, String> getNewAccount() {
        Map<String, String> account = new HashMap<>();
        account.put("investorId", String.valueOf(this.accountNumber.incrementAndGet()));
        account.put("investorName", this.NAME);
        account.put("email", this.EMAIL);
        account.put("credits", this.CREDITS_AMOUNT);
        return account;
    }

    private Map<String, String> getAccountWithId(Long id) {
        Map<String, String> account = new HashMap<>();
        account.put("investorId", String.valueOf(id));
        account.put("investorName", this.NAME);
        account.put("email", this.EMAIL);
        account.put("credits", this.CREDITS_AMOUNT);
        return account;
    }

    private Map<String, String> getNewAccountWithCredits(Long initialCredits) {
        Map<String, String> account = new HashMap<>();
        account.put("investorId", String.valueOf(this.accountNumber.incrementAndGet()));
        account.put("investorName", this.NAME);
        account.put("email", this.EMAIL);
        account.put("credits", String.valueOf(initialCredits));
        return account;
    }

    private Map<String, String> getNewValidTransaction() {
        Map<String, String> stock = new HashMap<>();
        stock.put("symbol", "MSFT");
        stock.put("market", "NASDAQ");

        Map<String, String> transaction = new HashMap<>();
        transaction.put("type", String.valueOf(this.accountNumber.incrementAndGet()));
        transaction.put("date", this.NAME);
        transaction.put("stock", this.EMAIL);
        transaction.put("credits", this.CREDITS_AMOUNT);
        return transaction;
    }
}
