package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.NotEnoughCreditsException;
import trading.domain.Account.NotEnoughCreditsForFeesException;
import trading.domain.Credits.Credits;
import trading.domain.transaction.InvalidTransactionNumberException;
import trading.domain.transaction.NotEnoughStockException;
import trading.domain.transaction.StockParametersDontMatchException;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionSell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    public static final long MORE_QUANTITY_WANTED_THEN_REMAINING_STOCKS = 3L;
    private static final Long VALID_INVESTOR_ID = 1L;
    private static final Long QUANTITY = 2L;
    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
    private static final int ACCOUNT_ID = 1;
    private static final Credits AN_ACCOUNT_BALANCE = Credits.fromDouble(1000);
    private static final Credits NOT_ENOUGH_CREDITS = Credits.fromDouble(0);
    private static final Credits TOO_MANY_FEES = Credits.fromDouble(10000);
    private static final Credits PRICE_TOO_HIGH = Credits.fromDouble(10000);
    private static final Credits FEES = Credits.fromDouble(100);
    private static final Credits PRICE = Credits.fromDouble(100);
    private static final InvestorProfile INVESTOR_PROFILE = new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<>());
    private static final Map<TransactionNumber, Long> REMAINING_STOCKS_MAP = new HashMap<>();
    private static final TransactionNumber A_TRANSACTION_NUMBER = new TransactionNumber();
    private final Stock STOCK1 = new Stock("mrkt", "symbl");
    private final Stock STOCK2 = new Stock("mrkt2", "symbl");

    @Mock
    TransactionSell transactionSell;
    @Mock
    TransactionBuy transactionBuy;
    private Account basicAccount;
    private Account notEnoughCreditsAccount;

    @Before
    public void setup() {
        REMAINING_STOCKS_MAP.put(A_TRANSACTION_NUMBER, QUANTITY);
        this.basicAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, AN_ACCOUNT_BALANCE, AN_ACCOUNT_BALANCE, INVESTOR_PROFILE, REMAINING_STOCKS_MAP, ACCOUNT_ID);
        this.notEnoughCreditsAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, NOT_ENOUGH_CREDITS, AN_ACCOUNT_BALANCE, INVESTOR_PROFILE, REMAINING_STOCKS_MAP, ACCOUNT_ID);
        when(this.transactionSell.getStock()).thenReturn(this.STOCK1);
        when(this.transactionBuy.getStock()).thenReturn(this.STOCK1);
    }

    @Test
    public void whenCreatingNewAccount_thenProfileTypeIsConservative() {
        assertEquals(ProfileType.CONSERVATIVE, this.basicAccount.getInvestorProfile().getProfileType());
    }

    @Test(expected = NotEnoughCreditsException.class)
    public void givenNotEnoughCreditsToPayTransaction_whenBuyTransaction_thenThrowNotEnoughCreditsException() {
        when(this.transactionBuy.getValueWithFees()).thenReturn(PRICE_TOO_HIGH);
        when(this.transactionBuy.getTransactionNumber()).thenReturn(new TransactionNumber());
        this.notEnoughCreditsAccount.buyTransaction(this.transactionBuy);
    }

    @Test
    public void givenValidBuyTransaction_whenBuyTransaction_thenPayTotalPrice() {
        when(this.transactionBuy.getValueWithFees()).thenReturn(FEES);
        when(this.transactionBuy.getTransactionNumber()).thenReturn(new TransactionNumber());
        Credits remainingCredits = this.basicAccount.getCredits();
        Credits totalPrice = this.transactionBuy.getValueWithFees();
        remainingCredits.subtract(totalPrice);
        this.basicAccount.buyTransaction(this.transactionBuy);
        assertEquals(this.basicAccount.getCredits(), remainingCredits);
    }

    @Test(expected = StockParametersDontMatchException.class)
    public void givenNotSameStock_whenSellTransaction_thenThrowStockParametersDontMatchException() {
        when(this.transactionSell.getStock()).thenReturn(this.STOCK2);
        this.notEnoughCreditsAccount.sellTransaction(this.transactionSell, this.transactionBuy);
    }

    @Test(expected = NotEnoughCreditsForFeesException.class)
    public void givenNotEnoughCreditsToPayFees_whenSellTransaction_thenThrowNotEnoughCreditsForFeesException() {
        when(this.transactionSell.getFees()).thenReturn(TOO_MANY_FEES);
        when(this.transactionBuy.getTransactionNumber()).thenReturn(A_TRANSACTION_NUMBER);
        this.notEnoughCreditsAccount.sellTransaction(this.transactionSell, this.transactionBuy);
    }

    @Test(expected = InvalidTransactionNumberException.class)
    public void givenDifferentTransactionNumberSoNoRemainingStockFound_whenSellTransaction_thenThrowInvalidTransactionNumberException() {
        when(this.transactionBuy.getTransactionNumber()).thenReturn(new TransactionNumber());
        this.basicAccount.sellTransaction(this.transactionSell, this.transactionBuy);
    }

    @Test(expected = NotEnoughStockException.class)
    public void givenDifferentTransactionNumberSoNoRemainingStockFound_whenSellTransaction_thenThrowNotEnoughStockException() {
        when(this.transactionSell.getQuantity()).thenReturn(MORE_QUANTITY_WANTED_THEN_REMAINING_STOCKS);
        when(this.transactionBuy.getTransactionNumber()).thenReturn(A_TRANSACTION_NUMBER);
        this.basicAccount.sellTransaction(this.transactionSell, this.transactionBuy);
    }

    @Test
    public void givenValidSellTransaction_whenSellTransaction_thenPayFeesAndAddTransactionMoney() {
        when(this.transactionSell.getValue()).thenReturn(PRICE);
        when(this.transactionSell.getFees()).thenReturn(FEES);
        when(this.transactionBuy.getTransactionNumber()).thenReturn(A_TRANSACTION_NUMBER);
        Credits remainingCredits = this.basicAccount.getCredits();
        remainingCredits.subtract(this.transactionSell.getFees());
        remainingCredits.add(this.transactionSell.getValue());
        this.basicAccount.sellTransaction(this.transactionSell, this.transactionBuy);
        assertEquals(this.basicAccount.getCredits(), remainingCredits);
    }

}
