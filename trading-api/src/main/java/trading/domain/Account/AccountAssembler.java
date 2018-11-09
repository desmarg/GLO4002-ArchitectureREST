package trading.domain.Account;

import trading.api.request.AccountPostRequestDTO;

public class AccountAssembler {
    public static Account create(AccountPostRequestDTO accountPostRequestDto) {

        return new Account(
                accountPostRequestDto.investorId,
                accountPostRequestDto.investorName,
                accountPostRequestDto.credits
        );
    }
}
