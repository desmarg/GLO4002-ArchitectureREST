package trading.domain.Transaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TransactionTest {
    private final double FEE_FOR_100_OR_MORE_TRANSACTIONS = 0.25;
    private final double FEE_UNDER_100_TRANSACTIONS = 0.20;
    private final Long VALID_QUANTITY_SMALLER_THAN_HUNDRED = 10L;
    private final Long VALID_QUANTITY_BIGGER_THAN_HUNDRED = 200L;
    private final DateTime VALID_DATE = new DateTime("2018-08-21T15:23:20.142Z");
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
        Credits transactionPrice = this.transaction.getPrice();

        BigDecimal expectedTransactionPrice = new BigDecimal(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED).multiply
                (this.SMALL_STOCK_PRICE.getAmount());
        assertEquals(expectedTransactionPrice, transactionPrice.getAmount());
    }

    @Test
    public void
    givenTotalLessThan5000AndQuantitySmallerThan100_whenCalculatingFees_thenQuarterRateFees() {
        Credits transactionFees = this.transaction.getFees();

        Credits expectedFees = Credits.fromDouble(this.FEE_FOR_100_OR_MORE_TRANSACTIONS);
        expectedFees.multiply(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void
    givenTotalLessThan5000AndQuantityBiggerThan100_whenCalculatingFees_thenFifthRateFees() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.SMALL_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.getFees();

        Credits expectedFees = Credits.fromDouble(this.FEE_UNDER_100_TRANSACTIONS);
        expectedFees.multiply(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void givenTotalPriceMoreThan5000_whenCalculatingFees_thenAdditionalRateAdded() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.LARGE_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.getFees();

        Credits expectedFees = Credits.fromDouble(this.FEE_UNDER_100_TRANSACTIONS);
        expectedFees.multiply(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED);
        Credits additionalFees = new Credits(this.transaction.getPrice());
        additionalFees.multiply(0.03);
        expectedFees.add(additionalFees);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }
}
