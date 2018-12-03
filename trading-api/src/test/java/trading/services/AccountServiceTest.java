//package trading.services;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import trading.api.request.AccountPostRequestDTO;
//import trading.domain.account.Account;
//import trading.domain.account.AccountNumber;
//import trading.domain.account.AccountRepository;
//
//import java.math.BigDecimal;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AccountServiceTest {
//
//    private static final String ACCOUNT_NUMBER = "TA-123";
//    private static final Long INVESTOR_ID = 1L;
//    private static final String INVESTOR_NAME = "Example Name";
//    private static final BigDecimal CREDITS = new BigDecimal(1.1);
//
//    private final AccountPostRequestDTO accountPostRequestDto = new AccountPostRequestDTO();
//    private AccountService accountService;
//
//    @Mock
//    private Account account;
//    @Mock
//    private AccountRepository accountRepository;
//    private String accountNumber;
//
//    @Before
//    public void setUp() {
//        this.accountService = new AccountService(this.accountRepository);
//        this.accountNumber = ACCOUNT_NUMBER;
//    }
//
//    @Test
//    public void givenAccount_whenUpdate_thenAccountRepositoryCalled() {
//        this.accountService.update(this.account);
//
//        verify(this.accountRepository).update(this.account);
//    }
//
//    @Test
//    public void givenAccountPostRequestDto_whenSave_thenSaveIsCalledInAccountRepository() {
//
//        this.accountPostRequestDto.credits = CREDITS;
//        this.accountPostRequestDto.investorId = INVESTOR_ID;
//        this.accountPostRequestDto.investorName = INVESTOR_NAME;
//
//        this.accountService.save(this.accountPostRequestDto);
//
//        verify(this.accountRepository).save(any());
//    }
//
//    @Test
//    public void whenFindByAccountNumber_thenFindAccountNumberInRepository() {
//        this.accountService.findByAccountNumber(this.accountNumber);
//        AccountNumber accountNumber = new AccountNumber(this.accountNumber);
//        verify(this.accountRepository).findByAccountNumber(accountNumber);
//    }
//}
