package trading.domain.Account;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Credits.Credits;

public class AccountAssembler {
    public static Account create(AccountPostRequestDTO accountPostRequestDto) {

        return new Account(
                accountPostRequestDto.investorId,
                accountPostRequestDto.investorName,
                new Credits(accountPostRequestDto.credits)
        );
    }
}
