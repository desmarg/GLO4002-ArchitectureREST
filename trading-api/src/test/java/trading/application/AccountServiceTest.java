package trading.application;

import domain.Account;
import domain.AccountNumber;
import exception.AccountAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import persistence.AccountRepository;
import services.AccountService;

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
        this.accountService = new AccountService(accountRepository);
    }

    @Test
    public void whenSaveAccount_thenAddAccountToRepository() {
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
        this.accountService.checkIfAccountExists(INVESTOR_ID);
        verify(this.accountRepository).checkIfAccountExists(INVESTOR_ID);
    }

    @Test(expected = AccountAlreadyExistsException.class)
    public void givenNoneExistingInvestorId_whenCheckIfAccountExists_thenThrowAccountAlreadyExistsException() {
        when(this.accountRepository.checkIfAccountExists(INVESTOR_ID)).thenReturn(true);
        this.accountService.checkIfAccountExists(INVESTOR_ID);
    }

    @Test
    public void whenNextAccountNumber_thenGetNextCounterValueInRepository() {
        this.accountService.nextAccountNumber();
        verify(this.accountRepository).nextCounterValue();
    }

}