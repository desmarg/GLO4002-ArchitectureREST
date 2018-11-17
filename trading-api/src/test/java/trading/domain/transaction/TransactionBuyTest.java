package trading.domain.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Credits;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBuyTest {
    private final DateTime VALID_DATE = new DateTime(Instant.parse("2018-08-21T15:23:20.142Z"));
    private final Credits SOME_STOCK_PRICE = new Credits(new BigDecimal(123));
    private final AccountNumber VALID_ACCOUNT_NUMBER = new AccountNumber("TD-0000");
    private final Credits PRICE_WITH_FEE = new Credits(new BigDecimal(123.25));
    private Stock stock;
    private TransactionBuy transactionBuy;

    @Before
    public void initialize() {
        Long VALID_QUANTITY = 1L;
        this.transactionBuy = new TransactionBuy(VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
    }

    @Test
    public void givenValidTransactionBuy_whenGetValueWithFees_thenReturnValidTotalPrice() {
        Credits totalPrice = this.transactionBuy.getValueWithFees();
        assertEquals(totalPrice, this.PRICE_WITH_FEE);
    }
}
