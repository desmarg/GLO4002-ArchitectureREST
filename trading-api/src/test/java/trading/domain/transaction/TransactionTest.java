package trading.domain.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {
    private final Long VALID_QUANTITY_SMALLER_THAN_HUNDRED = 10L;
    private final Long VALID_QUANTITY_BIGGER_THAN_HUNDRED = 200L;
    private final DateTime VALID_DATE = new DateTime(Instant.parse("2018-08-21T15:23:20.142Z"));
    private final Credits SMALL_STOCK_PRICE = new Credits(new BigDecimal(10), Currency.CAD);
    private final Credits LARGE_STOCK_PRICE = new Credits(new BigDecimal(10000), Currency.CAD);
    private final AccountNumber VALID_ACCOUNT_NUMBER = new AccountNumber("TD-0000");
    private final BigDecimal FEE_UNDER_OR_EQ_100 = new BigDecimal("0.25");
    private final BigDecimal FEE_OVER_100 = new BigDecimal("0.20");
    @Mock
    private Stock stock;
    private TransactionBuy transaction;

    @Before
    public void setUp() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.SMALL_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
    }

    @Test
    public void whenCalculatingTransactionPrice_thenStockPriceIsMultipliedByQuantity() {
        Credits transactionPrice = this.transaction.calculateValue();

        BigDecimal expectedTransactionPrice =
                new BigDecimal(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED).multiply(this.SMALL_STOCK_PRICE.getAmount());
        assertEquals(expectedTransactionPrice, transactionPrice.getAmount());
    }

    @Test
    public void givenTotalPriceLessThan5000AndQuantitySmallerThan100_whenCalculatingFees_thenQuarterRateFees() {
        Credits transactionFees = this.transaction.calculateFees();

        Credits expectedFees = new Credits(this.FEE_UNDER_OR_EQ_100.multiply(new BigDecimal(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED)), Currency.CAD);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void givenTotalPriceLessThan5000AndQuantityBiggerThan100_whenCalculatingFees_thenFifthRateFees() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.SMALL_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.calculateFees();

        Credits expectedFees = new Credits(this.FEE_OVER_100.multiply(new BigDecimal(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED)), Currency.CAD);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void givenTotalPriceMoreThan5000_whenCalculatingFees_thenAdditionalRateAdded() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.LARGE_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.calculateFees();

        Credits additionalFees = this.transaction.calculateValue().multiply(Transaction.FEE_OVER_5000);
        Credits expectedFees = new Credits(this.FEE_OVER_100.multiply(new BigDecimal(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED)), Currency.CAD).add(additionalFees);
        assertEquals(expectedFees, transactionFees);
    }
}
