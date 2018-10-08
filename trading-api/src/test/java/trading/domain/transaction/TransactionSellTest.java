package trading.domain.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.InvalidQuantityException;
import trading.exception.NotEnoughStockException;
import trading.exception.StockParametersDontMatchException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionSellTest {
    @Mock
    private Account account;

    @Mock
    private TransactionBuy transactionBuy;

    @Mock
    private Stock stock;

    @Captor
    private ArgumentCaptor<Credits> creditsArgumentCaptor;


    private Long INVALID_QUANTITY = 0L;
    private Long VALID_BUY_QUANTITY = 100L;
    private Long VALID_SELL_QUANTITY = 50L;
    private DateTime VALID_DATE = new DateTime("2018-08-21T15:23:20.142Z");
    private Credits SOME_STOCK_PRICE = new Credits(new BigDecimal(123));
    private TransactionNumber SOME_TRANSACTION_NUMBER = new TransactionNumber();

    @Before
    public void setUp() {
        this.transactionBuy = spy(new TransactionBuy(this.VALID_BUY_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE));
    }

    @Test(expected = InvalidQuantityException.class)
    public void
    givenQuantitySmallerThanOne_whenSellingStock_thenThrowInvalidQuantityException() {
        when(this.account.getTransaction(any(TransactionNumber.class))).thenReturn(this.transactionBuy);
        TransactionSell transactionSell = new TransactionSell(this.INVALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE, this.SOME_TRANSACTION_NUMBER);

        transactionSell.make(this.account);
    }

    @Test(expected = StockParametersDontMatchException.class)
    public void
    givenSoldStockDifferentThanBoughtStock_whenSellingStock_thenThrowStockParametersDontMatchException() {
        when(this.account.getTransaction(any(TransactionNumber.class))).thenReturn(this.transactionBuy);
        TransactionSell transactionSell = new TransactionSell(this.VALID_SELL_QUANTITY, this.VALID_DATE, new
                Stock(), this.SOME_STOCK_PRICE, this.SOME_TRANSACTION_NUMBER);

        transactionSell.make(this.account);
    }

    @Test(expected = NotEnoughStockException.class)
    public void givenNotEnoughStock_whenSellingStock_thenThrowNotEnoughStockException() {
        when(this.transactionBuy.hasEnoughStock(any(Long.class))).thenReturn(false);
        when(this.account.getTransaction(any(TransactionNumber.class))).thenReturn(this.transactionBuy);
        TransactionSell transactionSell = new TransactionSell(this.VALID_SELL_QUANTITY, this.VALID_DATE,
                this.stock, this.SOME_STOCK_PRICE, this.SOME_TRANSACTION_NUMBER);

        transactionSell.make(this.account);
    }

    @Test
    public void
    givenValidTransaction_whenSellingStock_thenCorrectQuantityDeducedFromTransactionBuy() {
        when(this.account.getTransaction(any(TransactionNumber.class))).thenReturn(this.transactionBuy);
        TransactionSell transactionSell = new TransactionSell(this.VALID_SELL_QUANTITY, this.VALID_DATE,
                this.stock, this.SOME_STOCK_PRICE, this.SOME_TRANSACTION_NUMBER);

        transactionSell.make(this.account);

        verify(this.transactionBuy, times(1)).deduceStock(this.VALID_SELL_QUANTITY);
    }

    @Test
    public void givenValidTransaction_whenSellingStock_thenAddTransactionPriceToAccountCredits() {
        when(this.account.getTransaction(any(TransactionNumber.class))).thenReturn(this.transactionBuy);
        TransactionSell transactionSell = new TransactionSell(this.VALID_SELL_QUANTITY, this.VALID_DATE,
                this.stock, this.SOME_STOCK_PRICE, this.SOME_TRANSACTION_NUMBER);

        transactionSell.make(this.account);

        Credits expectedAddedCredits = transactionSell.getPrice();
        verify(this.account, times(1)).addCredits(this.creditsArgumentCaptor.capture());
        assertEquals(expectedAddedCredits.getAmount(), this.creditsArgumentCaptor.getValue().getAmount());
    }

    @Test
    public void givenValidTransaction_whenSellingStock_thenSubtractTransactionFeesToAccountCredits
            () {
        when(this.account.getTransaction(any(TransactionNumber.class))).thenReturn(this.transactionBuy);
        TransactionSell transactionSell = new TransactionSell(this.VALID_SELL_QUANTITY, this.VALID_DATE,
                this.stock, this.SOME_STOCK_PRICE, this.SOME_TRANSACTION_NUMBER);

        transactionSell.make(this.account);

        Credits expectedSubtractedCredits = transactionSell.getFees();
        verify(this.account, times(1)).subtractCredits(this.creditsArgumentCaptor.capture());
        assertEquals(expectedSubtractedCredits.getAmount(), this.creditsArgumentCaptor.getValue().getAmount());
    }

    @Test
    public void givenValidTransaction_whenSellingStock_thenTransactionAddedToAccount() {
        when(this.account.getTransaction(any(TransactionNumber.class))).thenReturn(this.transactionBuy);
        TransactionSell transactionSell = new TransactionSell(this.VALID_SELL_QUANTITY, this.VALID_DATE,
                this.stock, this.SOME_STOCK_PRICE, this.SOME_TRANSACTION_NUMBER);

        transactionSell.make(this.account);

        verify(this.account, times(1)).addTransaction(transactionSell);
    }
}