package application;

import api.account.AccountCreatorDto;
import persistence.AccountRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountCreatorDto account;
    @Mock
    private AccountRepository accountRepository;
    private AccountService accountService;

    @Before
    public void setUp(){
        this.accountService = new AccountService(accountRepository);
    }

    @Test
    public void whenCreatingANewAccount_thenDelegateToRepository(){
        this.accountService.create(this.account);
        BDDMockito.verify(this.accountRepository).add(this.account);
    }
}