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

import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static final Long ACCOUNT_NUMBER = 1L; //"TA-123"
    private static final Long INVESTOR_ID = 1L;
    private static final String INVESTOR_NAME = "Example Name";
    private static final BigDecimal CREDITS = new BigDecimal(1.1);

    private AccountPostRequestDTO accountPostRequestDto = new AccountPostRequestDTO();
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        this.accountService = new AccountService(this.accountRepository);
    }

    @Test
    public void givenAccountPostRequestDto_whenSave_thenSaveIsCalledInAccountRepository() {

        this.accountPostRequestDto.credits = CREDITS;
        this.accountPostRequestDto.investorId = INVESTOR_ID;
        this.accountPostRequestDto.investorName = INVESTOR_NAME;

        when(this.accountRepository.accountAlreadyExists(any(Long.class))).thenReturn(false);
        this.accountService.save(this.accountPostRequestDto);

        verify(this.accountRepository).save(any());
    }

    @Test
    public void whenFindByAccountNumber_thenFindAccountNumberInRepository() {
        this.accountService.findByAccountNumber(ACCOUNT_NUMBER);

        verify(this.accountRepository).findByAccountNumber(ACCOUNT_NUMBER);
    }
}
