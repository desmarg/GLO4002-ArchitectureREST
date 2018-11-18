package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.account.AccountNumber;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountNumberTest {
    private static final String AN_ACCOUNT_NUMBER = "JT-0001";
    private static final String EQUAL_ACCOUNT_NUMBER = "JT-0001";
    private static final String DIFFERENT_ACCOUNT_NUMBER = "AA-2";
    private static final String A_NAME = "Jeffrey Tramblay";
    private static final String INITIALS = "JT";
    private static final int ID = 1;

    private AccountNumber accountNumber;

    @Before
    public void setup() {
        this.accountNumber = new AccountNumber(AN_ACCOUNT_NUMBER);
    }

    @Test
    public void givenTwoIdenticalAccountNumbers_whenEquals_thenReturnTrue() {
        AccountNumber similarAccountNumber = new AccountNumber(EQUAL_ACCOUNT_NUMBER);

        assertTrue(this.accountNumber.equals(similarAccountNumber));
    }

    @Test
    public void givenTwoDifferentAccountNumbers_whenEquals_thenReturnFalse() {
        AccountNumber differentAccountNumber = new AccountNumber(DIFFERENT_ACCOUNT_NUMBER);

        assertFalse(this.accountNumber.equals(differentAccountNumber));
    }

    @Test
    public void givenName_whenMakingInitials_thenReturnGoodInitials() {
        assertEquals(INITIALS, this.accountNumber.makeInitials(A_NAME));
    }

    @Test
    public void whenGettingId_thenReturnGoodId() {
        assert (ID == this.accountNumber.getId());
    }
}
