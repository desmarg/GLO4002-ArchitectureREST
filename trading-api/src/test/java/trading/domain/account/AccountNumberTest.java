package trading.domain.account;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.account.AccountNumber;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountNumberTest {
    private static final String AN_ACCOUNT_NUMBER = "AA-1";
    private static final String EQUAL_ACCOUNT_NUMBER = "AA-1";
    private static final String DIFFERENT_ACCOUNT_NUMBER = "AA-2";
    private static final String A_NAME = "Jeffrey Tramblay";
    private static final String INITIALS = "JT";

    private AccountNumber accountNumber;

    @Before
    public void setup() {
        this.accountNumber = new AccountNumber(AN_ACCOUNT_NUMBER);
    }

    @Test
    public void givenTwoIdenticalAccountNumbers_whenEqualsAccountNumbers_thenReturnTrue() {
        AccountNumber similarAccountNumber = new AccountNumber(EQUAL_ACCOUNT_NUMBER);

        assertTrue(this.accountNumber.equals(similarAccountNumber));
    }

    @Test
    public void givenTwoDifferentAccountNumbers_whenEqualsAccountNumbers_thenReturnFalse() {
        AccountNumber differentAccountNumber = new AccountNumber(DIFFERENT_ACCOUNT_NUMBER);

        assertFalse(this.accountNumber.equals(differentAccountNumber));
    }

    @Test
    public void givenName_whenMakingInitials_thenReturnGoodInitials() {
        assertEquals(INITIALS, this.accountNumber.makeInitials(A_NAME));
    }
}
