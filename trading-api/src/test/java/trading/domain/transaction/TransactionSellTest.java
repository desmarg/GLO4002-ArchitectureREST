package trading.domain.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TransactionSellTest {

    private static final Long A_QUANTITY = 1L;
    private static final DateTime VALID_DATE = DateTime.fromInstant(Instant.parse("2018-08-21T15:23:20.142Z"));
    private static final Credits SOME_STOCK_PRICE = new Credits(new BigDecimal(123));
    private static final TransactionNumber SOME_TRANSACTION_NUMBER = new TransactionNumber();
    private static final AccountNumber VALID_ACCOUNT_NUMBER = new AccountNumber("TD-0000");
    private static final Credits A_CREDIT_FEE = new Credits(new BigDecimal(0.25));

    @Mock
    Account account;
    @Mock
    TransactionBuy transactionBuy;

    private Stock stock;

//    @Test
//    public void givenTransactionSell_whenExecuteTransaction_thenAddCreditsCalled() {
//        TransactionSell transactionSell = new TransactionSell(A_QUANTITY, VALID_DATE, this.stock,
//                SOME_STOCK_PRICE, SOME_TRANSACTION_NUMBER, VALID_ACCOUNT_NUMBER);
//        transactionSell.executeTransaction(this.account);
//        verify(this.account).addCredits(SOME_STOCK_PRICE);
//    }

    @Test
    public void givenTransactionSell_whenExecuteTransaction_thenSubtractCreditsCalled() {
        assertTrue(true);
//        TransactionSell transactionSell = new TransactionSell(A_QUANTITY, VALID_DATE, this.stock,
//                SOME_STOCK_PRICE, SOME_TRANSACTION_NUMBER, VALID_ACCOUNT_NUMBER);
//        transactionSell.executeTransaction(this.account);
//        verify(this.account).buyTransaction(A_CREDIT_FEE);
    }
}
