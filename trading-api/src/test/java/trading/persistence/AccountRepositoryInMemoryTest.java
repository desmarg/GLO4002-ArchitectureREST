package trading.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.exception.AccountNotFoundException;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryInMemoryTest {

    private static final AccountNumber AN_ACCOUNT_NUMBER = new AccountNumber("TA-123");
    private static final AccountNumber NON_EXISTING_ACCOUNT_NUMBER = new AccountNumber("TA-456");

    private static final long AN_INVESTOR_ID = 456L;
    private static final long NON_EXISTING_INVESTOR_ID = 456L;

    @Mock
    private Account account;

    private AccountRepositoryInMemory accountRepositoryInMemory;

    @Before
    public void setUp() {
        this.accountRepositoryInMemory = new AccountRepositoryInMemory();
        BDDMockito.willReturn(AN_ACCOUNT_NUMBER).given(this.account).getAccountNumber();
    }

    @Test
    public void whenAccountIsNotInRepository_thenAccountExistsReturnFalse() {
        assertFalse(this.accountRepositoryInMemory.accountAlreadyExists(NON_EXISTING_INVESTOR_ID));
    }

    @Test(expected = AccountNotFoundException.class)
    public void givenNonexistentAccount_whenFindingAccount_thenThrowAccountNotFoundException() {
        this.accountRepositoryInMemory.findByAccountNumber(NON_EXISTING_ACCOUNT_NUMBER);
    }
}
