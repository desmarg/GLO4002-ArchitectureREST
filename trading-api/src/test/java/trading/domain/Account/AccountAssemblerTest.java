package trading.domain.Account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.AccountPostRequestDTO;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class AccountAssemblerTest {
    private final AccountPostRequestDTO accountPostRequestDTO = new AccountPostRequestDTO();

    private int accountId;

    @Test(expected = InvalidCreditsAmountException.class)
    public void givenInvalidCreditsAmount_whenValidating_thenThrowInvalidCreditsAmountException() {
        this.accountPostRequestDTO.investorName = "Bob";
        this.accountPostRequestDTO.credits = BigDecimal.ZERO;
        this.accountPostRequestDTO.investorId = 1L;
        this.accountPostRequestDTO.email = "t@g";

        AccountAssembler.create(this.accountPostRequestDTO, this.accountId);
    }
}