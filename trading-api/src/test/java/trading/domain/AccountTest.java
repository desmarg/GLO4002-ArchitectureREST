package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.exception.InvalidCreditsAmountException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final Long VALID_INVESTOR_ID = 1l;
    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
    private static final String VALID_EMAIL = "first.last@example.com";
    private static final AccountNumber accountNumber = new AccountNumber("FL-0001");
    private static final Credits INVALID_INITIAL_CREDITS = Credits.fromDouble(-10);
    private static final Credits A_CREDIT_ACCOUNT = Credits.fromDouble(10);

    @Mock
    private Credits credits;
    credits

    private Account basicAccount;


    @Before
    public void setup() {
        this.basicAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, VALID_EMAIL, this.credits, accountNumber);
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
    public void givenAccountWithEnoughCredits_whenSubtractingCredits_thenSubtractIsCalledOnCreditsWithGoodAmount() {
        this.basicAccount.subtractCredits(A_CREDIT_ACCOUNT);
        verify(this.credits).subtract(A_CREDIT_ACCOUNT);
    }
}
