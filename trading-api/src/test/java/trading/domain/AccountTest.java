package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static final Long VALID_INVESTOR_ID = 1l;
    private static final String VALID_INVESTOR_NAME = "FirstName LastName";
    private static final String VALID_EMAIL = "first.last@example.com";
    private static final AccountNumber accountNumber = new AccountNumber("FL-0001");

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

}
