package domain.account;

import domain.Credits;
import domain.investorprofile.InvestorProfile;
import domain.stock.Stock;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;
import exception.InvalidQuantityException;
import exception.NotEnoughCreditsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    @Mock
    private AccountNumber accountNumber;

    @Mock
    private InvestorProfile investorProfile;

    @Mock
    private Credits credits;

    @Mock
    private Credits transactionPrice;

    @Mock
    private Transaction transaction;

    private static Long INSUFFICIENT_QUANTITY = 0L;

    private static Long SUFFICIENT_QUANTITY = 10L;
    @Mock
    private Stock stock;

    private Account account;
    private Long investorId;
    private String investorName;
    private String email;
    private TransactionNumber transactionNumber;
    private Map<TransactionNumber, Transaction> transactionList;
    private Map<TransactionNumber, Long> stockWallet;

    @Before
    public void setup() {
        this.account = new Account(this.investorId, this.investorName, this.email, this.credits, this.accountNumber);
        this.transactionNumber = new TransactionNumber();
    }

    @Test
    public void givenTransaction_whenBuyTransaction_thenTransactionAddedToTransactionList() {
        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
        this.account.buyTransaction(this.transaction);
        assertEquals(1, this.account.getTransactionList().size());
    }

    @Test
    public void givenTransaction_whenBuyTransaction_thenCalculateTransactionPriceIsCalled() {
        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
        this.account.buyTransaction(this.transaction);
        verify(this.transaction).calculateTransactionPrice();
    }

    @Test(expected = NotEnoughCreditsException.class)
    public void givenTransactionWithNotEnoughCredits_whenBuyTransaction_thenThrowNotEnoughCreditsException() {
        when(this.transaction.calculateTransactionPrice()).thenReturn(this.transactionPrice);
        when(this.credits.compareTo(this.transactionPrice)).thenReturn(-1);
        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);

        this.account.buyTransaction(this.transaction);
    }

    @Test(expected = InvalidQuantityException.class)
    public void givenTransactionWithNotEnoughCredits_whenBuyTransaction_thenThrowInvalidQuantityException() {
        when(this.transaction.calculateTransactionPrice()).thenReturn(this.transactionPrice);
        when(this.transaction.getQuantity()).thenReturn(INSUFFICIENT_QUANTITY);

        this.account.buyTransaction(this.transaction);
    }

    @Test
    public void givenTransaction_whenBuyTransaction_thenCreditsSubstractIsCalled() {
        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
        this.account.buyTransaction(this.transaction);
        verify(this.credits).subtract(this.transaction.calculateTransactionPrice());
    }

    @Test
    public void givenTransaction_whenBuyTransaction_thenTransactionAddedToStockWalletList() {
        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
        this.account.buyTransaction(this.transaction);
        assertEquals(1, this.account.getStockWallet().size());
    }

    @Test
    public void givenTransactionNumber_whenGetTransaction_thenReturnedFoundTransaction() {
        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
        this.account.buyTransaction(this.transaction);
        Transaction transactionFound = this.account.getTransactionList().get(this.transaction.getTransactionNumber());
        assertEquals(transactionFound, this.transaction);
    }
}