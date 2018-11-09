package trading.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.AccountPostRequestDTO;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.Account.AccountAlreadyExistsException;
import trading.domain.Account.AccountRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static final AccountNumber ACCOUNT_NUMBER = new AccountNumber("TA-123");
    private static final Long INVESTOR_ID = 1L;
    private static final String INVESTOR_NAME = "Example Name";
    private static final Credits CREDITS = Credits.fromDouble(1.1);

    private AccountPostRequestDTO accountPostRequestDto = new AccountPostRequestDTO();
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountNumber accountNumber;

    @Before
    public void setUp() {
        this.accountService = new AccountService(this.accountRepository);
        this.accountNumber = ACCOUNT_NUMBER;
    }

    @Test
    public void givenAccountPostRequestDto_whenSave_thenSaveAccountToRepository() {

        this.accountPostRequestDto.credits = CREDITS;
        this.accountPostRequestDto.investorId = INVESTOR_ID;
        this.accountPostRequestDto.investorName = INVESTOR_NAME;

        when(this.accountRepository.accountAlreadyExists(any(Long.class))).thenReturn(false);
        Account savedAccount = this.accountService.save(this.accountPostRequestDto);

        verify(this.accountRepository).save(savedAccount);
    }

    @Test
    public void whenFindByAccountNumber_thenFindAccountNumberInRepository() {
        this.accountService.findByAccountNumber(this.accountNumber);

        verify(this.accountRepository).findByAccountNumber(this.accountNumber);
    }
}
