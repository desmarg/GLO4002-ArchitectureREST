package trading.domain.transaction;

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
import trading.exception.NotEnoughCreditsException;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBuyTest {
    @Mock
    private Account account;

    @Mock
    private Stock stock;

    @Captor
    private ArgumentCaptor<Credits> creditsArgumentCaptor;

    private Long INVALID_QUANTITY = 0L;
    private Long VALID_QUANTITY = 100L;
    private DateTime VALID_DATE = new DateTime("2018-08-21T15:23:20.142Z");
    private Credits SOME_STOCK_PRICE = new Credits(new BigDecimal(123));

    @Test(expected = InvalidQuantityException.class)
    public void givenBuyQuantitySmallerThanOne_whenMakingTransaction_thenThrowInvalidQuantityException() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);
        TransactionBuy transactionBuy = new TransactionBuy(this.INVALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE);

        transactionBuy.make(this.account);
    }

    @Test(expected = NotEnoughCreditsException.class)
    public void givenNotEnoughCredits_whenMakingTransaction_thenThrowNotEnoughCreditsException() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(false);
        TransactionBuy transactionBuy = new TransactionBuy(this.VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE);

        transactionBuy.make(this.account);
    }

    @Test
    public void
    givenValidTransaction_whenMakingTransaction_thenSubtractTotalPriceFromAccount() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);
        TransactionBuy transactionBuy = new TransactionBuy(this.VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE);

        transactionBuy.make(this.account);

        Credits expectedTotalPrice = transactionBuy.getTotalPrice();
        verify(this.account).subtractCredits(this.creditsArgumentCaptor.capture());
        assertEquals(expectedTotalPrice.getAmount(), this.creditsArgumentCaptor.getValue().getAmount());
    }

    @Test
    public void givenValidTransaction_whenMakingTransaction_thenTransactionIsAddedToAccount() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);
        TransactionBuy transactionBuy = new TransactionBuy(this.VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE);

        transactionBuy.make(this.account);

        verify(this.account).addTransaction(transactionBuy);
    }

    @Test
    public void givenNotEnoughStocks_whenCheckingEnoughStocks_thenReturnFalse() {
        TransactionBuy transactionBuy = new TransactionBuy(this.VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE);

        assertFalse(transactionBuy.hasEnoughStock(this.VALID_QUANTITY + 1));
    }

    @Test
    public void givenEnoughStocks_whenCheckingEnoughStocks_thenReturnTrue() {
        TransactionBuy transactionBuy = new TransactionBuy(this.VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE);

        assertTrue(transactionBuy.hasEnoughStock(this.VALID_QUANTITY - 1));
    }
}
