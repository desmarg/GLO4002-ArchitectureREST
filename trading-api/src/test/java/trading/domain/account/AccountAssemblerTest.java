package trading.domain.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import trading.api.request.AccountPostRequestDTO;
import trading.api.request.CreditsDTO;

import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class AccountAssemblerTest {
    private final AccountPostRequestDTO accountPostRequestDTO = new AccountPostRequestDTO();

    @Test(expected = InvalidCreditsAmountException.class)
    public void givenInvalidCreditsAmount_whenValidating_thenThrowInvalidCreditsAmountException() {
        CreditsDTO creditsDTO = new CreditsDTO();
        creditsDTO.amount = BigDecimal.ZERO;
        creditsDTO.currency = "CAD";
        ArrayList<CreditsDTO> credits = new ArrayList<>();
        credits.add(creditsDTO);

        this.accountPostRequestDTO.investorName = "Bob";
        this.accountPostRequestDTO.credits = credits;
        this.accountPostRequestDTO.investorId = 1L;
        this.accountPostRequestDTO.email = "t@g";

        Integer AN_ACCOUNT_ID = 0;
        AccountAssembler.create(this.accountPostRequestDTO, AN_ACCOUNT_ID);
    }
}