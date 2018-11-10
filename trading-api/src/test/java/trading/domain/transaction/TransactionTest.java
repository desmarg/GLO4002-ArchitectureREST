package trading.domain.transaction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TransactionTest {
    private class TransactionImplementation extends Transaction {
        public TransactionImplementation(Long quantity, DateTime dateTime, Stock stock, Credits
                stockPrice, Long accountNumber) {
            super(quantity, dateTime, stock, stockPrice, accountNumber);
        }

        public void make(Account account) {

        }
    }

    @Mock
    private Stock stock;

    private Long VALID_QUANTITY_SMALLER_THAN_HUNDRED = 10L;
    private Long VALID_QUANTITY_BIGGER_THAN_HUNDRED = 200L;
    private DateTime VALID_DATE = new DateTime("2018-08-21T15:23:20.142Z");
    private Credits SMALL_STOCK_PRICE = new Credits(new BigDecimal(10));
    private Credits LARGE_STOCK_PRICE = new Credits(new BigDecimal(10000));
    private Long VALID_ACCOUNT_NUMBER = 1L;//"TD-0000"
    private TransactionBuy transaction;

    @Before
    public void initialize() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.SMALL_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
    }

    @Test
    public void whenCalculatingTransactionPrice_thenStockPriceIsMultipliedByQuantity() {


        Credits transactionPrice = this.transaction.calculateTransactionPrice();

        BigDecimal expectedTransactionPrice = new BigDecimal(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED).multiply
                (this.SMALL_STOCK_PRICE.getAmount());
        assertEquals(expectedTransactionPrice, transactionPrice.getAmount());
    }

    @Test
    public void
    givenTotalLessThan5000AndQuantitySmallerThan100_whenCalculatingFees_thenQuarterRateFees
            () {

        Credits transactionFees = this.transaction.calculateFees();

        Credits expectedFees = Credits.fromDouble(0.25);
        expectedFees.multiply(this.VALID_QUANTITY_SMALLER_THAN_HUNDRED);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void
    givenTotalLessThan5000AndQuantityBiggerThan100_whenCalculatingFees_thenFifthRateFees
            () {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.SMALL_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.calculateFees();

        Credits expectedFees = Credits.fromDouble(0.20);
        expectedFees.multiply(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void givenTotalPriceMoreThan5000_whenCalculatingFees_thenAdditionalRateAdded() {
        this.transaction = new TransactionBuy(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                this.VALID_DATE, this.stock, this.LARGE_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
        Credits transactionFees = this.transaction.calculateFees();

        Credits expectedFees = Credits.fromDouble(0.20);
        expectedFees.multiply(this.VALID_QUANTITY_BIGGER_THAN_HUNDRED);
        Credits additionalFees = new Credits(this.transaction.calculateTransactionPrice());
        additionalFees.multiply(0.03);
        expectedFees.add(additionalFees);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }
}
