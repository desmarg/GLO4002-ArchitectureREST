package trading.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.exception.AccountAlreadyExistsException;
import trading.persistence.AccountRepository;
import trading.services.AccountService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static long INVESTOR_ID = 10;
    @Mock
    private Account account;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountNumber accountNumber;
    private AccountService accountService;

    @Before
    public void setUp() {
        this.accountService = new AccountService(this.accountRepository);
    }

    @Test
    public void whenSaveAccount_thenAddAccountToRepository() {
        when(this.accountRepository.accountAlreadyExists(any(Long.class))).thenReturn(false);
        this.accountService.saveAccount(this.account);
        verify(this.accountRepository).add(this.account);
    }

    @Test
    public void whenFindByAccountNumber_thenFindAccountNumberInRepository() {
        this.accountService.findByAccountNumber(this.accountNumber);
        verify(this.accountRepository).findByAccountNumber(this.accountNumber);
    }

    @Test
    public void givenInvestorId_whenCheckIfAccountExists_thenCheckIfAccountExistsInRepository() {
        this.accountService.checkIfAccountAlreadyExists(INVESTOR_ID);
        verify(this.accountRepository).accountAlreadyExists(INVESTOR_ID);
    }

    @Test(expected = AccountAlreadyExistsException.class)
    public void givenExistingAccount_whenCheckIfAccountExists_thenThrowAccountAlreadyExistsException() {
        when(this.accountRepository.accountAlreadyExists(any(Long.class))).thenReturn(true);
        this.accountService.checkIfAccountAlreadyExists(INVESTOR_ID);
    }

    @Test
    public void whenNextAccountNumber_thenGetNextCounterValueInRepository() {
        this.accountService.nextAccountNumber();
        verify(this.accountRepository).nextCounterValue();
    }

}