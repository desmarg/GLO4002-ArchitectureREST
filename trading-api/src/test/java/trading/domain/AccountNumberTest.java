package trading.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountNumberTest {
    private static String ACCOUNT_NUMBER = "AA-1";
    private static String DIFFERENT_ACCOUNT_NUMBER = "AA-2";

    @Test
    public void givenAccountNumber_whenEqualsAValidAccountNumber_thenReturnTrue() {
        AccountNumber accountNumber = new AccountNumber(ACCOUNT_NUMBER);
        AccountNumber similarAccountNumber = new AccountNumber(ACCOUNT_NUMBER);

        assertEquals(true, accountNumber.equals(similarAccountNumber));
    }

    @Test
    public void givenAccountNumber_whenEqualsADifferentAccountNumber_thenReturnFalse() {
        AccountNumber accountNumber = new AccountNumber(ACCOUNT_NUMBER);
        AccountNumber differentAccountNumber = new AccountNumber(DIFFERENT_ACCOUNT_NUMBER);
        assertEquals(false, accountNumber.equals(differentAccountNumber));
    }
}