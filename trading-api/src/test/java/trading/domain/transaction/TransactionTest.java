package trading.domain.transaction;

import org.junit.Test;
import org.mockito.Mock;
import trading.domain.Account;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TransactionTest {
    private class TransactionImplementation extends Transaction {
        public TransactionImplementation(Long quantity, DateTime dateTime, Stock stock, Credits
                stockPrice) {
            super(quantity, dateTime, stock, stockPrice);
        }

        public void make(Account account) {

        }
    }

    @Mock
    private Stock stock;

    private Long VALID_QUANTITY_SMALLER_THAN_HUNDRED = 10L;
    private Long VALID_QUANTITY_BIGGER_THAN_HUNDRED = 200L;
    private DateTime VALID_DATE = new DateTime("2018-08-21T15:23:20.142Z");
    private Credits SOME_STOCK_PRICE = new Credits(new BigDecimal(10));
    private Credits LARGE_STOCK_PRICE = new Credits(new BigDecimal(10000));
    private TransactionImplementation transaction;

    @Test
    public void whenCalculatingTransactionPrice_thenStockPriceIsMultipliedByQuantity() {
        transaction = new TransactionImplementation(VALID_QUANTITY_SMALLER_THAN_HUNDRED,
                VALID_DATE, stock, SOME_STOCK_PRICE);

        Credits transactionPrice = transaction.calculateTransactionPrice();

        BigDecimal expectedTransactionPrice = new BigDecimal(VALID_QUANTITY_SMALLER_THAN_HUNDRED).multiply
                (SOME_STOCK_PRICE.getAmount());
        assertEquals(expectedTransactionPrice, transactionPrice.getAmount());
    }

    @Test
    public void
            givenTotalLessThan5000AndQuantitySmallerThan100_whenCalculatingFees_thenQuarterRateFees
            () {
        transaction = new TransactionImplementation(VALID_QUANTITY_SMALLER_THAN_HUNDRED,
                VALID_DATE, stock, SOME_STOCK_PRICE);

        Credits transactionFees = transaction.calculateFees();

        Credits expectedFees = Credits.fromDouble(0.25);
        expectedFees.multiply(VALID_QUANTITY_SMALLER_THAN_HUNDRED);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void
    givenTotalLessThan5000AndQuantityBiggerThan100_whenCalculatingFees_thenFifthRateFees
            () {
        transaction = new TransactionImplementation(VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                VALID_DATE, stock, SOME_STOCK_PRICE);

        Credits transactionFees = transaction.calculateFees();

        Credits expectedFees = Credits.fromDouble(0.20);
        expectedFees.multiply(VALID_QUANTITY_BIGGER_THAN_HUNDRED);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }

    @Test
    public void givenTotalPriceMoreThan5000_whenCalculatingFees_thenAdditionalRateAdded() {
        transaction = new TransactionImplementation(VALID_QUANTITY_BIGGER_THAN_HUNDRED,
                VALID_DATE, stock, LARGE_STOCK_PRICE);

        Credits transactionFees = transaction.calculateFees();

        Credits expectedFees = Credits.fromDouble(0.20);
        expectedFees.multiply(VALID_QUANTITY_BIGGER_THAN_HUNDRED);
        Credits additionalFees = new Credits(transaction.calculateTransactionPrice());
        additionalFees.multiply(0.03);
        expectedFees.add(additionalFees);
        assertEquals(expectedFees.getAmount(), transactionFees.getAmount());
    }
}