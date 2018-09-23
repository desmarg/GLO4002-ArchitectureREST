package persistence;

import domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepositoryInMemoryTest {

    private static final long AN_ACCOUNT_NUMBER = 123L;
    private static final long NON_EXISTING_ACCOUNT_NUMBER = 456L;

    @Mock
    private Account account;
    private AccountRepositoryInMemory accountRepositoryInMemory;

    @Before
    public void setUp(){
        accountRepositoryInMemory = new AccountRepositoryInMemory();
        BDDMockito.willReturn(AN_ACCOUNT_NUMBER).given(account).getAccountNumber();
    }

    @Test
    public void whenAddingValidAccount_thenAccountIsAdded(){
        accountRepositoryInMemory.add(account);
        assertEquals(account, accountRepositoryInMemory.findByAccountNumber(AN_ACCOUNT_NUMBER));
    }

    @Test
    public void givenCreatedAccount_whenFindingAccountByAccountNumber_thenReturnAccount(){
        accountRepositoryInMemory.add(account);
        assertEquals(account, accountRepositoryInMemory.findByAccountNumber(AN_ACCOUNT_NUMBER));

    }

    @Test(expected = AccountNotFoundException.class)
    public void givenNonexistentAccount_whenFindingAccountByAccountNumber_thenThrowAccountNotFoundException(){
        accountRepositoryInMemory.findByAccountNumber(NON_EXISTING_ACCOUNT_NUMBER);
    }

    @Test(expected = AccountAlreadyExistsException.class)
    public void givenExistingAccount_whenCreatingTheSameAccount_thenThrowAccountAlreadyExistsException(){
        accountRepositoryInMemory.add(account);
        accountRepositoryInMemory.add(account);
    }

}