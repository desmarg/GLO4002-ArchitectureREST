package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits;
import trading.exception.AccountAlreadyExistsException;
import trading.persistence.AccountRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber("TA-123");
    private static final Long INVESTOR_ID = 1l;
    private static final String INVESTOR_NAME = "Example Name";
    private static final String EMAIL = "example@mail.com";
    private static final Credits CREDITS = Credits.fromDouble(1.1);

    private Account account;
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountNumber accountNumber;

    @Before
    public void setUp() {
        this.accountService = new AccountService(this.accountRepository);
        this.accountNumber = ACCOUNT_NUMBER;
        this.account = new Account(
                INVESTOR_ID,
                INVESTOR_NAME,
                EMAIL,
                CREDITS
        );
    }

    @Test
    public void whenSave_thenSaveAccountToRepository() {
        when(this.accountRepository.accountAlreadyExists(any(Long.class))).thenReturn(false);
        this.accountService.save(this.account);

        verify(this.accountRepository).save(this.account);
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
    public void
    givenExistingAccount_whenCheckIfAccountExists_thenThrowAccountAlreadyExistsException() {
        when(this.accountRepository.accountAlreadyExists(any(Long.class))).thenReturn(true);

        this.accountService.checkIfAccountAlreadyExists(INVESTOR_ID);
    }
}
