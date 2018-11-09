package trading.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountNumberTest {
    private static final String AN_ACCOUNT_NUMBER = "AA-1";
    private static final String EQUAL_ACCOUNT_NUMBER = "AA-1";
    private static final String DIFFERENT_ACCOUNT_NUMBER = "AA-2";
    private static final String A_NAME = "Jeffrey Tremblay";
    private static final String INITIALS = "JT";
    private static final long A_LONG = 23;
    private static final String ID = "JT-0023";

    private static AccountNumber accountNumber;

    @Before
    public void setUp() {
        this.accountNumber = new AccountNumber(AN_ACCOUNT_NUMBER);
    }

    @Test
    public void whenComparingTwoIdenticalAccounts_thenEqualsReturnTrue() {
        AccountNumber similarAccountNumber = new AccountNumber(EQUAL_ACCOUNT_NUMBER);

        assertEquals(true, accountNumber.equals(similarAccountNumber));
    }

    @Test
    public void givenAccountNumber_whenEqualsADifferentAccountNumber_thenReturnFalse() {
        AccountNumber differentAccountNumber = new AccountNumber(DIFFERENT_ACCOUNT_NUMBER);

        assertEquals(false, accountNumber.equals(differentAccountNumber));
    }

    @Test
    public void givenName_whenMakingInitials_thenReturnTheGoodInitials() {
        assertEquals(INITIALS, accountNumber.makeInitials(A_NAME));
    }

    @Test
    public void givenNameAndLong_whenMakingId_thenMakeValidId() {
        assertEquals(ID, accountNumber.makeId(A_NAME, A_LONG));
    }
}
