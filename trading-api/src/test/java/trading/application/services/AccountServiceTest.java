package trading.application.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.AccountPostRequestDTO;
import trading.api.request.CreditsDTO;
import trading.domain.account.Account;
import trading.domain.account.AccountNumber;
import trading.domain.account.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static final String ACCOUNT_NUMBER = "TA-123";
    private static final Long INVESTOR_ID = 1L;
    private static final String INVESTOR_NAME = "Example Name";

    private final AccountPostRequestDTO accountPostRequestDto = new AccountPostRequestDTO();
    private AccountApplicationService accountService;

    @Mock
    private Account account;
    @Mock
    private AccountRepository accountRepository;
    private String accountNumber;

    @Before
    public void setUp() {
        this.accountService = new AccountApplicationService(this.accountRepository);
        this.accountNumber = ACCOUNT_NUMBER;
    }

    @Test
    public void givenAccount_whenUpdate_thenAccountRepositoryCalled() {
        this.accountService.update(this.account);

        verify(this.accountRepository).update(this.account);
    }

    @Test
    public void givenAccountPostRequestDto_whenSave_thenSaveIsCalledInAccountRepository() {
        CreditsDTO creditsDTO = new CreditsDTO();
        creditsDTO.currency = "CAD";
        creditsDTO.amount = new BigDecimal(1.1);
        ArrayList<CreditsDTO> credits = new ArrayList<>();
        credits.add(creditsDTO);
        this.accountPostRequestDto.credits = credits;
        this.accountPostRequestDto.investorId = INVESTOR_ID;
        this.accountPostRequestDto.investorName = INVESTOR_NAME;

        this.accountService.createAccount(this.accountPostRequestDto);

        verify(this.accountRepository).save(any());
    }

    @Test
    public void whenFindByAccountNumber_thenFindAccountNumberInRepository() {
        this.accountService.findByAccountNumber(this.ACCOUNT_NUMBER);
        AccountNumber accountNumber = new AccountNumber(this.ACCOUNT_NUMBER);

        verify(this.accountRepository).findByAccountNumber(accountNumber);
    }
}
