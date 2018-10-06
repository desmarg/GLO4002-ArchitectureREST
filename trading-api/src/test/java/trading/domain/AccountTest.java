package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.exception.InvalidAccountInfoException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final Long VALID_INVESTOR_ID = 1l;
    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
    private static final String VALID_EMAIL = "first.last@example.com";
    private static final AccountNumber accountNumber = new AccountNumber("FL-0001");

    private static final String INVALID_EMAIL = "first.last.com";
    private static final String INVALID_INVESTOR_NAME = "12345";

    private Credits credits = Credits.fromDouble(100);

    private Account basicAccount;


    @Before
    public void setup() {
        this.basicAccount = new Account(VALID_INVESTOR_ID, VALID_INVESTOR_NAME, VALID_EMAIL, this.credits, accountNumber);
    }

    @Test
    public void whenCreatingNewAccount_thenProfileTypeIsConservative() {
        assertEquals(ProfileType.CONSERVATIVE, this.basicAccount.getInvestorProfile().getProfileType());
    }

    @Test(expected = InvalidAccountInfoException.class)
    public void givenInvalidEmail_whenValidating_thenThrowInvalidAccountInfoException() {
        this.basicAccount.validateEmail(INVALID_EMAIL);
    }

    @Test(expected = InvalidAccountInfoException.class)
    public void givenInvalidInvestorName_whenValidating_thenThrowInvalidAccountInfoException() {
        this.basicAccount.validateInvestorName(INVALID_INVESTOR_NAME);
    }


}
