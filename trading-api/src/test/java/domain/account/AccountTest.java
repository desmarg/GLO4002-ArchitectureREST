package domain.account;

import domain.Credits;
import domain.investorprofile.InvestorProfile;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;
import exception.NotEnoughCreditsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
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

    private Account account;
    private Long investorId;
    private String investorName;
    private String email;
    private Map<TransactionNumber, Transaction> transactionList;
    private Map<TransactionNumber, Long> stockWallet;

    @Before
    public void setup() {
        this.transactionList = new HashMap<>();
        this.stockWallet = new HashMap<>();
        this.account = new Account(this.investorId, this.investorName, this.email, this.credits, this.accountNumber);
    }

    @Test
    public void givenTransaction_whenBuyTransaction_thenCalculateTransactionPriceIsCalled() {
//        this.account.buyTransaction(this.transaction);
//        verify(this.transaction).calculateTransactionPrice();
    }
//
//    @Test(expected = NotEnoughCreditsException.class)
//    public void givenTransactionWithNotEnoughCredits_whenBuyTransaction_thenThrowNotEnoughCreditsException() {
//        when(this.transaction.calculateTransactionPrice()).thenReturn(this.transactionPrice);
//        when(this.credits.compareTo(this.transactionPrice)).thenReturn(-1);
//
//        this.account.buyTransaction(this.transaction);
//    }
//
//    @Test
//    public void givenTransaction_whenBuyTransaction_thenCreditsSubstractIsCalled() {
//        this.account.buyTransaction(this.transaction);
//        verify(this.credits).subtract(this.transaction.calculateTransactionPrice());
//    }
//
//    @Test
//    public void givenTransaction_whenBuyTransaction_thenTransactionAddedToTransactionList() {
//        this.account.buyTransaction(this.transaction);
//
//        assertEquals(1, this.account.getTransactionList().size());
//    }
//
//    @Test
//    public void givenTransaction_whenBuyTransaction_thenTransactionAddedToStockWalletList() {
//        this.account.buyTransaction(this.transaction);
//
//        assertEquals(1, this.account.getStockWallet().size());
//    }
//
//    @Test
//    public void givenTransactionNumber_whenGetTransaction_thenReturnedFoundTransaction() {
//
//        this.account.buyTransaction(this.transaction);
//        Transaction transactionFound = this.account.getTransactionList().get(this.transaction.getTransactionNumber());
//
//        assertEquals(transactionFound, this.transaction);
//    }
}