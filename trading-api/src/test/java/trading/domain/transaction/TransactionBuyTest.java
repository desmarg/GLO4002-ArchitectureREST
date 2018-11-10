package trading.domain.transaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Account.NotEnoughCreditsException;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBuyTest {
    private final Long INVALID_QUANTITY = 0L;
    private final Long VALID_QUANTITY = 100L;
    private final DateTime VALID_DATE = new DateTime("2018-08-21T15:23:20.142Z");
    private final Credits SOME_STOCK_PRICE = new Credits(new BigDecimal(123));
    private final AccountNumber VALID_ACCOUNT_NUMBER = new AccountNumber("TD-0000");

    @Mock
    private Account account;
    private Stock stock;
    private TransactionBuy transactionBuy;

    @Before
    public void initialize() {
        this.transactionBuy = new TransactionBuy(this.VALID_QUANTITY, this.VALID_DATE, this.stock,
                this.SOME_STOCK_PRICE, this.VALID_ACCOUNT_NUMBER);
    }

    @Test(expected = NotEnoughCreditsException.class)
    public void givenNotEnoughCredits_whenMakingTransaction_thenThrowNotEnoughCreditsException() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(false);

        this.transactionBuy.executeTransaction(this.account);
    }

    @Test
    public void givenValidTransaction_whenMakingTransaction_thenSubtractTotalPriceFromAccount() {
        when(this.account.hasEnoughCreditsToPay(any(Credits.class))).thenReturn(true);

        this.transactionBuy.executeTransaction(this.account);

        Credits expectedTotalPrice = this.transactionBuy.getTotalPrice();
        verify(this.account).buyTransaction(expectedTotalPrice);
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
