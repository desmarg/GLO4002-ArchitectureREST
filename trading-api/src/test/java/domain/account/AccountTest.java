package domain.account;

import exception.TransactionNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

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

    @Mock
    private Stock stock;

    private static Long SUFFICIENT_QUANTITY = 10L;
    private static Long INSUFFICIENT_QUANTITY = 0L;

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

//    @Test
//    public void givenTransaction_whenBuyTransaction_thenTransactionAddedToTransactionList() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        this.account.buyTransaction(this.transaction);
//        assertEquals(1, this.account.getTransactionList().size());
//    }
//
//    @Test
//    public void givenTransaction_whenBuyTransaction_thenCalculateTransactionPriceIsCalled() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        this.account.buyTransaction(this.transaction);
//        verify(this.transaction).calculateTransactionPrice();
//    }
//
//    @Test(expected = NotEnoughCreditsException.class)
//    public void givenTransactionWithNotEnoughCredits_whenBuyTransaction_thenThrowNotEnoughCreditsException() {
//        when(this.transaction.calculateTransactionPrice()).thenReturn(this.transactionPrice);
//        when(this.credits.compareTo(this.transactionPrice)).thenReturn(-1);
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//
//        this.account.buyTransaction(this.transaction);
//    }

//    @Test(expected = InvalidQuantityException.class)
//    public void givenTransactionWithNotEnoughCredits_whenBuyTransaction_thenThrowInvalidQuantityException() {
//        when(this.transaction.calculateTransactionPrice()).thenReturn(this.transactionPrice);
//        when(this.transaction.getQuantity()).thenReturn(INSUFFICIENT_QUANTITY);
//
//        this.account.buyTransaction(this.transaction);
//    }
//
//    @Test
//    public void givenTransaction_whenBuyTransaction_thenCreditsSubstractIsCalled() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        this.account.buyTransaction(this.transaction);
//        verify(this.credits).subtract(this.transaction.calculateTransactionPrice());
//    }
//
//    @Test
//    public void givenTransaction_whenBuyTransaction_thenTransactionAddedToStockWalletList() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        this.account.buyTransaction(this.transaction);
//        assertEquals(1, this.account.getStockWallet().size());
//    }
//
//    @Test
//    public void givenTransactionNumber_whenGetTransaction_thenReturnedFoundTransaction() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        this.account.buyTransaction(this.transaction);
//        Transaction transactionFound = this.account.getTransactionList().get(this.transaction.getTransactionNumber());
//        assertEquals(transactionFound, this.transaction);
//    }
//
//    @Test
//    public void givenTransaction_whenSellTransaction_thenTransactionAddedToTransactionList() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        when(this.transaction.getStock()).thenReturn(this.stock);
//        when(this.transaction.getTransactionNumber()).thenReturn(this.transactionNumber);
//        when(this.transaction.getReferredTransactionNumber()).thenReturn(this.transactionNumber);
//        this.account.getStockWallet().put(this.transactionNumber, SUFFICIENT_QUANTITY);
//        this.account.sellTransaction(this.transaction);
//        assertEquals(1, this.account.getTransactionList().size());
//    }

    @Test
    public void givenValidTransactionNumberThatExistInList_whenGetTransactionFromHistory_thenReferredTransactionReturned() {
        this.account.getTransactionList().put(this.transactionNumber, this.transaction);
        Transaction referredTransactionReturned = this.account.getTransaction(this.transactionNumber);
        assertEquals(this.transaction, referredTransactionReturned);
    }

    @Test(expected = TransactionNotFoundException.class)
    public void givenTransactionNumberThatDoesNotExistInList_whenGetTransactionFromHistory_thenThrowTransactionNotFoundException() {
        this.account.getTransaction(this.transactionNumber);
    }

//    @Test
//    public void givenReferredTransaction_whenGetRemainingStocks_thenRemainingStockIsReturned() {
//        when(this.transaction.getTransactionNumber()).thenReturn(this.transactionNumber);
//        this.account.getStockWallet().put(this.transactionNumber, SUFFICIENT_QUANTITY);
//        Long remainingStock = this.account.getRemainingStocks(this.transaction);
//    }
//
//    @Test(expected = InvalidTransactionNumberException.class)
//    public void givenReferredTransactionWithNotExistingTransactionNumberInStockWallet_whenGetRemainingStocks_thenThrowInvalidTransactionNumberException() {
//        this.account.getRemainingStocks(this.transaction);
//    }

//    @Test(expected = NotEnoughStockException.class)
//    public void givenTransaction_whenSellTransaction_thenThrowNotEnoughStockException() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        when(this.transaction.getStock()).thenReturn(this.stock);
//        when(this.transaction.getTransactionNumber()).thenReturn(this.transactionNumber);
//        when(this.transaction.getReferredTransactionNumber()).thenReturn(this.transactionNumber);
//        this.account.getStockWallet().put(this.transactionNumber, INSUFFICIENT_QUANTITY);
//        this.account.sellTransaction(this.transaction);
//    }

//    @Test
//    public void givenTransaction_whenSellTransaction_thenCalculateTransactionPriceCalled() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        when(this.transaction.getStock()).thenReturn(this.stock);
//        when(this.transaction.getTransactionNumber()).thenReturn(this.transactionNumber);
//        when(this.transaction.getReferredTransactionNumber()).thenReturn(this.transactionNumber);
//        this.account.getStockWallet().put(this.transactionNumber, SUFFICIENT_QUANTITY);
//        this.account.sellTransaction(this.transaction);
//        verify(this.transaction).calculateTransactionPrice();
//    }
//
//    @Test
//    public void givenTransaction_whenSellTransaction_thenCreditAddCalled() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        when(this.transaction.getStock()).thenReturn(this.stock);
//        when(this.transaction.getTransactionNumber()).thenReturn(this.transactionNumber);
//        when(this.transaction.getReferredTransactionNumber()).thenReturn(this.transactionNumber);
//        this.account.getStockWallet().put(this.transactionNumber, SUFFICIENT_QUANTITY);
//        this.account.sellTransaction(this.transaction);
//        verify(this.credits).add(any());
//    }
//
//    @Test
//    public void givenTransaction_whenSellTransaction_thenStockWalletIsUpdated() {
//        when(this.transaction.getQuantity()).thenReturn(SUFFICIENT_QUANTITY);
//        when(this.transaction.getStock()).thenReturn(this.stock);
//        when(this.transaction.getTransactionNumber()).thenReturn(this.transactionNumber);
//        when(this.transaction.getReferredTransactionNumber()).thenReturn(this.transactionNumber);
//        this.account.getStockWallet().put(this.transactionNumber, SUFFICIENT_QUANTITY);
//        this.account.sellTransaction(this.transaction);
//        assertEquals(1, this.account.getStockWallet().size());
//    }
}