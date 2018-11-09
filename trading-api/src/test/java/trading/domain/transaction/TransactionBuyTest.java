package trading.domain.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.domain.Account.NotEnoughCreditsException;

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
    private TransactionBuy transactionBuy;
    private AccountNumber VALID_ACCOUNT_NUMBER = new AccountNumber("TD-0000");


    @Before
    public void initialize() {
        this.transactionBuy = new TransactionBuy(this.VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
    }

    //TODO mettre dans test assembleur
//    @Test(expected = InvalidQuantityException.class)
//    public void givenBuyQuantitySmallerThanOne_whenMakingTransaction_thenThrowInvalidQuantityException() {
//        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);
//        TransactionBuy transactionBuy = new TransactionBuy(this.INVALID_QUANTITY, this.VALID_DATE, this.stock,
//                this.SOME_STOCK_PRICE);
//
//        transactionBuy.executeTransaction(this.account);
//    }

    @Test(expected = NotEnoughCreditsException.class)
    public void givenNotEnoughCredits_whenMakingTransaction_thenThrowNotEnoughCreditsException() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(false);

        this.transactionBuy.executeTransaction(this.account);
    }

    @Test
    public void
    givenValidTransaction_whenMakingTransaction_thenSubtractTotalPriceFromAccount() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);

        this.transactionBuy.executeTransaction(this.account);

        Credits expectedTotalPrice = this.transactionBuy.getTotalPrice();
        verify(this.account).subtractCredits(this.creditsArgumentCaptor.capture());
        assertEquals(expectedTotalPrice.getAmount(), this.creditsArgumentCaptor.getValue().getAmount());
    }

    @Test
    public void givenNotEnoughStocks_whenCheckingEnoughStocks_thenReturnFalse() {

        assertFalse(this.transactionBuy.hasEnoughStock(this.VALID_QUANTITY + 1));
    }

    @Test
    public void givenEnoughStocks_whenCheckingEnoughStocks_thenReturnTrue() {

        assertTrue(this.transactionBuy.hasEnoughStock(this.VALID_QUANTITY - 1));
    }
}
