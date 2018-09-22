import models.Account;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    @Test
    public void givenExistingCreatedAccount_whenCreatingANewAccount_thenItsAccountNumberIsOneMoreThanTheLatestCreated() {
        Account firstAccount = new Account();
        long firstAccountNumber = firstAccount.getAccountNumber();

        Account nextAccount = new Account();
        long nextAccountNumber = nextAccount.getAccountNumber();

        assertEquals(firstAccountNumber + 1, nextAccountNumber);
    }
}