package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.transaction.TransactionBuy;
import trading.persistence.TransactionRepositoryInMemory;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
    String accountNumber = "TD-0000";
    private final AccountNumber VALID_ACCOUNT_NUMBER = new AccountNumber(this.accountNumber);
    @Mock
    private AccountService accountService;
    private final TransactionService transactionService = new TransactionService(new TransactionRepositoryInMemory(), new StockService(), new MarketService(), this.accountService);
    @Mock
    private Account account;
    @Mock
    private TransactionBuy transactionBuy;

    @Before
    public void setup() {
    }

    @Test
    public void givenClosedMarket_whenExecuteTransactionBuy_thenThrowMarketClosedException() {

    }

//    @Test (expected = RuntimeException.class)
//    public void givenBuyStockNotFromAccount_whenValidateStockFromAccount_thenThrowException() {
//        when(this.account.getAccountNumber()).thenReturn(VALID_ACCOUNT_NUMBER);
//        when(this.transactionBuy.getAccountNumber()).thenReturn(VALID_ACCOUNT_NUMBER);
//        when(this.accountService.findByAccountNumber(any(AccountNumber.class))).thenReturn(account);
//        transactionService.(accountNumber, );
//        String accountNumber, TransactionPostRequestDTO transactionPostRequestDTO
//    }

    @Test
    public void givenClosedMarket_whenExecuteTransactionSell_thenThrowMarketClosedException() {

    }

//    @Test
//    public void givenTransactionSell_whenExecuteTransaction_thenDecuceStockCalled() {
//        TransactionSell transactionSell = new TransactionSell(A_QUANTITY, VALID_DATE, this.stock,
//                SOME_STOCK_PRICE, SOME_TRANSACTION_NUMBER, VALID_ACCOUNT_NUMBER);
//        transactionSell.executeTransaction(this.account);
//        verify(this.transactionBuy).deduceStock(A_QUANTITY);
//    }

}