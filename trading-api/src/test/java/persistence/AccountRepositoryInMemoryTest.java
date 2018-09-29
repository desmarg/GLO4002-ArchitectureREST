package persistence;

import api.account.AccountCreatorDto;
import domain.Account;
import domain.AccountNumber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryInMemoryTest {

    private static final Long AN_ACCOUNT_NUMBER = 123L;
    private static final AccountNumber NON_EXISTING_ACCOUNT_NUMBER = new AccountNumber(456L);

    @Mock
    private Account account;

    private AccountRepositoryInMemory accountRepositoryInMemory;

    @Before
    public void setUp(){
        accountRepositoryInMemory = new AccountRepositoryInMemory();
        BDDMockito.willReturn(AN_ACCOUNT_NUMBER).given(account).getAccountNumber();
    }

    @Test
    public void whenAccountIsNotInRepository_thenAccountExistsReturnFalse(){
        assertFalse(accountRepositoryInMemory.checkIfAccountExists(AN_ACCOUNT_NUMBER));
    }

    @Test(expected = AccountNotFoundException.class)
    public void givenNonexistentAccount_whenFindingAccount_thenThrowAccountNotFoundException() {
        accountRepositoryInMemory.findByAccountNumber(NON_EXISTING_ACCOUNT_NUMBER);
    }
}
