//package trading.domain;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import trading.domain.Account.Account;
//import trading.domain.Account.AccountNumber;
//import trading.domain.Account.NotEnoughCreditsException;
//import trading.domain.Account.NotEnoughCreditsForFeesException;
//import trading.domain.Credits.Credits;
//import trading.domain.transaction.TransactionBuy;
//import trading.domain.transaction.TransactionNumber;
//import trading.domain.transaction.TransactionSell;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AccountTest {
//
//    private static final Long VALID_INVESTOR_ID = 1L;
//    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
//    private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber("FL-0001");
//    private static final Credits AN_ACCOUNT_BALANCE = Credits.fromDouble(1000);
//    private static final Credits NOT_ENOUGH_CREDITS = Credits.fromDouble(0);
//    private static final Credits TOO_MANY_FEES = Credits.fromDouble(10000);
//    private static final Credits PRICE_TOO_HIGH = Credits.fromDouble(10000);
//    private static final Credits FEES = Credits.fromDouble(100);
//    private static final Credits PRICE = Credits.fromDouble(100);
//
//
//    private Account basicAccount;
//    private Account notEnoughCreditsAccount;
//
//    @Mock
//    TransactionSell transactionSell;
//    @Mock
//    TransactionBuy transactionBuy;
//
//    @Before
//    public void setup() {
//        this.basicAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, AN_ACCOUNT_BALANCE);
//        this.notEnoughCreditsAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, NOT_ENOUGH_CREDITS);
//        this.basicAccount.setAccountNumber(ACCOUNT_NUMBER);
//    }
//
//    @Test
//    public void whenCreatingNewAccount_thenProfileTypeIsConservative() {
//        assertEquals(ProfileType.CONSERVATIVE, this.basicAccount.getInvestorProfile().getProfileType());
//    }
//
//    @Test(expected = NotEnoughCreditsForFeesException.class)
//    public void givenNotEnoughCreditsToPayFees_whenSellTransaction_thenThrowNotEnoughCreditsForFeesException() {
//        when(this.transactionSell.getFees()).thenReturn(TOO_MANY_FEES);
//        this.notEnoughCreditsAccount.sellTransaction(this.transactionSell);
//    }
//
//    @Test
//    public void givenValidSellTransaction_whenSellTransaction_thenPayFeesAndAddTransactionMoney() {
//        when(this.transactionSell.getValue()).thenReturn(PRICE);
//        when(this.transactionSell.getFees()).thenReturn(FEES);
//        Credits remainingCredits = this.basicAccount.getCredits();
//        remainingCredits.subtract(this.transactionSell.getFees());
//        remainingCredits.add(this.transactionSell.getValue());
//        this.basicAccount.sellTransaction(this.transactionSell);
//        assertEquals(this.basicAccount.getCredits(), remainingCredits);
//    }
//
//    @Test(expected = NotEnoughCreditsException.class)
//    public void givenNotEnoughCreditsToPayTransaction_whenBuyTransaction_thenThrowNotEnoughCreditsException() {
//        when(this.transactionBuy.getValueWithFees()).thenReturn(PRICE_TOO_HIGH);
//        when(this.transactionBuy.getTransactionNumber()).thenReturn(new TransactionNumber());
//        this.notEnoughCreditsAccount.buyTransaction(this.transactionBuy);
//    }
//
//    @Test
//    public void givenValidBuyTransaction_whenBuyTransaction_thenPayTotalPrice() {
//        when(this.transactionBuy.getValueWithFees()).thenReturn(FEES);
//        when(this.transactionBuy.getTransactionNumber()).thenReturn(new TransactionNumber());
//        Credits remainingCredits = this.basicAccount.getCredits();
//        Credits totalPrice = this.transactionBuy.getValueWithFees();
//        remainingCredits.subtract(totalPrice);
//        this.basicAccount.buyTransaction(this.transactionBuy);
//        assertEquals(this.basicAccount.getCredits(), remainingCredits);
//    }
//
//
//}
