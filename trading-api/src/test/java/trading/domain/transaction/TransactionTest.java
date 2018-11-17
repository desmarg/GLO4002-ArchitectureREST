package trading.domain.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.account.AccountNumber;
import trading.domain.Credits;
import trading.domain.datetime.DateTime;
import trading.domain.Stock;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {
    private final Long VALID_QUANTITY_SMALLER_THAN_HUNDRED = 10L;
    private final Long VALID_QUANTITY_BIGGER_THAN_HUNDRED = 200L;
    private final DateTime VALID_DATE = new DateTime(Instant.parse("2018-08-21T15:23:20.142Z"));
    private final Credits SMALL_STOCK_PRICE = new Credits(new BigDecimal(10));
    private final Credits LARGE_STOCK_PRICE = new Credits(new BigDecimal(10000));
    private final AccountNumber VALID_ACCOUNT_NUMBER = new AccountNumber("TD-0000");
    @Mock
    private Stock stock;
    private TransactionBuy transaction;

    @Before
    public void initialize() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.SMALL_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
    }

    @Test
    public void whenCalculatingTransactionPrice_thenStockPriceIsMultipliedByQuantity() {
        Credits transactionPrice = this.transaction.getValue();

        BigDecimal expectedTransactionPrice = new BigDecimal(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED).multiply
                (this.SMALL_STOCK_PRICE.toBigDecimal());
        assertEquals(expectedTransactionPrice, transactionPrice.toBigDecimal());
    }

    @Test
    public void
    givenTotalLessThan5000AndQuantitySmallerThan100_whenCalculatingFees_thenQuarterRateFees() {
        Credits transactionFees = this.transaction.getFees();

        Credits expectedFees = Transaction.FEE_OVER_OR_EQ_100
                .multiply(Credits.fromLong(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED));
        assertEquals(expectedFees.toBigDecimal(), transactionFees.toBigDecimal());
    }

    @Test
    public void
    givenTotalLessThan5000AndQuantityBiggerThan100_whenCalculatingFees_thenFifthRateFees() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.SMALL_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.getFees();

        Credits expectedFees = Transaction.FEE_UNDER_100
                .multiply(Credits.fromLong(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED));
        assertEquals(expectedFees.toBigDecimal(), transactionFees.toBigDecimal());
    }

    @Test
    public void givenTotalPriceMoreThan5000_whenCalculatingFees_thenAdditionalRateAdded() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.LARGE_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.getFees();

        Credits additionalFees = this.transaction.getValue()
                .multiply(Transaction.FEE_OVER_5000);
        Credits expectedFees = Transaction.FEE_UNDER_100
                .multiply(Credits.fromLong(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED))
                .add(additionalFees);
        assertEquals(expectedFees, transactionFees);
    }
}
