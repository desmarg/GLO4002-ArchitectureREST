package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Account.InvalidCreditsAmountException;
import trading.domain.Credits.Credits;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final Long VALID_INVESTOR_ID = 1L;
    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
    private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber("FL-0001");
    private static final Credits INVALID_INITIAL_CREDITS = Credits.fromDouble(-10);
    private static final Credits AN_ACCOUNT_BALANCE = Credits.fromDouble(1000);
    private static final Credits SOME_CREDITS_AMOUNT = Credits.fromDouble(100);
    private static final Credits LESS_CREDITS_THAN_IN_ACCOUNT = Credits.fromDouble(7);
    private static final Credits MORE_CREDITS_THAN_IN_ACCOUNT = Credits.fromDouble(6969);

    private Account basicAccount;


    @Before
    public void setup() {
        this.basicAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, AN_ACCOUNT_BALANCE);
        this.basicAccount.setId(ACCOUNT_NUMBER);
    }

    @Test
    public void whenCreatingNewAccount_thenProfileTypeIsConservative() {
        assertEquals(ProfileType.CONSERVATIVE, this.basicAccount.getInvestorProfile().getProfileType());
    }


    @Test(expected = InvalidCreditsAmountException.class)
    public void givenInvalidCreditsAmount_whenValidating_thenThrowInvalidCreditsAmountException() {
        this.basicAccount.validateInitialCredits(INVALID_INITIAL_CREDITS);
    }

    @Test
    public void givenSufficientFunds_whenCheckingBalance_thenReturnTrue() {
        assertTrue(this.basicAccount.hasEnoughCreditsToPay(LESS_CREDITS_THAN_IN_ACCOUNT));
    }

    @Test
    public void givenInsufficientFunds_whenCheckingBalance_thenReturnFalse() {
        assertFalse(this.basicAccount.hasEnoughCreditsToPay(MORE_CREDITS_THAN_IN_ACCOUNT));
    }


}
