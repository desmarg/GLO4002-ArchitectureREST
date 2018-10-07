package trading.domain.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.InvalidQuantityException;
import trading.exception.NotEnoughCreditsException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

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
    public
    void givenBuyQuantitySmallerThanOne_whenMakingTransaction_thenThrowInvalidQuantityException() {
        when(account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);
        TransactionBuy transactionBuy = new TransactionBuy(INVALID_QUANTITY, VALID_DATE, stock,
                SOME_STOCK_PRICE);

        transactionBuy.make(account);
    }

    @Test(expected = NotEnoughCreditsException.class)
    public void givenNotEnoughCredits_whenMakingTransaction_thenThrowNotEnoughCreditsException() {
        when(account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(false);
        TransactionBuy transactionBuy = new TransactionBuy(VALID_QUANTITY, VALID_DATE, stock,
                SOME_STOCK_PRICE);

        transactionBuy.make(account);
    }

    @Test
    public void
            givenValidTransaction_whenMakingTransaction_thenSubtractTotalPriceFromAccount() {
        when(account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);
        TransactionBuy transactionBuy = new TransactionBuy(VALID_QUANTITY, VALID_DATE, stock,
                SOME_STOCK_PRICE);

        transactionBuy.make(account);

        Credits expectedTotalPrice = transactionBuy.getTotalPrice();
        verify(account).subtractCredits(creditsArgumentCaptor.capture());
        assertEquals(expectedTotalPrice.getAmount(), creditsArgumentCaptor.getValue().getAmount());
    }

    @Test
    public void givenValidTransaction_whenMakingTransaction_thenTransactionIsAddedToAccount() {
        when(account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);
        TransactionBuy transactionBuy = new TransactionBuy(VALID_QUANTITY, VALID_DATE, stock,
                SOME_STOCK_PRICE);

        transactionBuy.make(account);

        verify(account).addTransaction(transactionBuy);
    }
}