package trading.domain.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.*;
import trading.domain.transaction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    private static final Long MORE_QUANTITY_WANTED_THEN_REMAINING_STOCKS = 3L;
    private static final Long VALID_INVESTOR_ID = 1L;
    private static final Long QUANTITY = 2L;
    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
    private static final Integer ACCOUNT_ID = 1;
    private static final HashMap<Currency, Credits> AN_ACCOUNT_BALANCE = new HashMap<>();
    private static final HashMap<Currency, Credits> NO_CREDITS = new HashMap<>();
    private static final Credits TOO_MANY_FEES = Credits.fromInteger(10000, Currency.CAD);
    private static final Credits PRICE_TOO_HIGH = Credits.fromInteger(10000, Currency.CAD);
    private static final Credits FEES = Credits.fromInteger(100, Currency.CAD);
    private static final Credits PRICE = Credits.fromInteger(100, Currency.CAD);
    private static final InvestorProfile INVESTOR_PROFILE =
            new InvestorProfile(ProfileType.CONSERVATIVE, new ArrayList<>());
    private static final Map<TransactionID, Long> REMAINING_STOCKS_PER_TRANSACTION_MAP = new HashMap<>();
    private static final TransactionID A_TRANSACTION_NUMBER = new TransactionID();
    private final Stock STOCK1 = new Stock("mrkt", "symbl");
    private final Stock STOCK2 = new Stock("mrkt2", "symbl");
    private Account basicAccount;
    private Account notEnoughCreditsAccount;

    @Mock
    private TransactionSell transactionSell;
    @Mock
    private TransactionBuy transactionBuy;


    public AccountTest() {
        AN_ACCOUNT_BALANCE.put(Currency.CAD, Credits.fromInteger(1000, Currency.CAD));
        NO_CREDITS.put(Currency.CAD, Credits.fromInteger(0, Currency.CAD));
    }

    @Before
    public void setup() {
        AccountNumber validAccountNumber = new AccountNumber(VALID_INVESTOR_NAME, ACCOUNT_ID);

        REMAINING_STOCKS_PER_TRANSACTION_MAP.put(A_TRANSACTION_NUMBER, QUANTITY);
        this.basicAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME,
                AN_ACCOUNT_BALANCE, AN_ACCOUNT_BALANCE, INVESTOR_PROFILE, REMAINING_STOCKS_PER_TRANSACTION_MAP,
                validAccountNumber);
        this.notEnoughCreditsAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME,
                NO_CREDITS, AN_ACCOUNT_BALANCE, INVESTOR_PROFILE, REMAINING_STOCKS_PER_TRANSACTION_MAP,
                validAccountNumber);
        when(this.transactionSell.getStock()).thenReturn(this.STOCK1);
        when(this.transactionBuy.getStock()).thenReturn(this.STOCK1);
    }

    @Test
    public void whenCreatingNewAccount_thenProfileTypeIsConservative() {
        assertEquals(ProfileType.CONSERVATIVE,
                this.basicAccount.getInvestorProfile().getType());
    }

    @Test(expected = NotEnoughCreditsException.class)
    public void givenNotEnoughCreditsToPayTransaction_whenBuyTransaction_thenThrowNotEnoughCreditsException() {
        when(this.transactionBuy.calculateValueWithFees()).thenReturn(PRICE_TOO_HIGH);
        when(this.transactionBuy.getTransactionID()).thenReturn(new TransactionID());

        this.notEnoughCreditsAccount.buyTransaction(this.transactionBuy);
    }

    @Test
    public void givenAccountWith1000Credits_whenBuyTransactionThatCost100Credits_thenAccountHas900CreditsRemaining() {
        when(this.transactionBuy.calculateValueWithFees()).thenReturn(FEES);
        when(this.transactionBuy.getTransactionID()).thenReturn(new TransactionID());
        HashMap<Currency, Credits> remainingCredits = this.basicAccount.getCredits();
        Credits totalPrice = this.transactionBuy.calculateValueWithFees();
        remainingCredits.merge(Currency.CAD, totalPrice, (a, b) -> a.subtract(b));

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
        when(this.transactionSell.calculateFees()).thenReturn(TOO_MANY_FEES);
        when(this.transactionBuy.getTransactionID()).thenReturn(A_TRANSACTION_NUMBER);

        this.notEnoughCreditsAccount.sellTransaction(this.transactionSell, this.transactionBuy);
    }

    @Test(expected = InvalidTransactionNumberException.class)
    public void givenTransactionSellWithInvalidTransactionID_whenSellTransaction_thenThrowInvalidTransactionNumberException() {
        when(this.transactionBuy.getTransactionID()).thenReturn(new TransactionID());

        this.basicAccount.sellTransaction(this.transactionSell, this.transactionBuy);
    }

    @Test(expected = NotEnoughStockException.class)
    public void givenNotEnoughRemainingStocksFOrTransactionSell_whenSellTransaction_thenThrowNotEnoughStockException() {
        when(this.transactionSell.getQuantity()).thenReturn(MORE_QUANTITY_WANTED_THEN_REMAINING_STOCKS);
        when(this.transactionBuy.getTransactionID()).thenReturn(A_TRANSACTION_NUMBER);

        this.basicAccount.sellTransaction(this.transactionSell, this.transactionBuy);
    }

    @Test
    public void givenValidSellTransaction_whenSellTransaction_thenPayFeesAndAddTransactionMoney() {
        when(this.transactionSell.calculateValue()).thenReturn(PRICE);
        when(this.transactionSell.calculateFees()).thenReturn(FEES);
        when(this.transactionBuy.getTransactionID()).thenReturn(A_TRANSACTION_NUMBER);
        HashMap<Currency, Credits> remainingCredits = this.basicAccount.getCredits();
        remainingCredits.merge(Currency.CAD, this.transactionSell.calculateFees(), (a, b) -> a.subtract(b));
        remainingCredits.merge(Currency.CAD, this.transactionSell.calculateValue(), (a, b) -> a.add(b));

        this.basicAccount.sellTransaction(this.transactionSell, this.transactionBuy);

        assertEquals(this.basicAccount.getCredits(), remainingCredits);
    }

}
