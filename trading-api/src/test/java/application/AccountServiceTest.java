package application;

import domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.AccountRepository;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private Account account;
    @Mock
    private AccountRepository accountRepository;
    private AccountService accountService;

    @Before
    public void setUp(){
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void whenCreatingANewAccount_thenDelegateToRepository(){
        accountService.create(account);
        BDDMockito.verify(accountRepository).add(account);
    }
}