package trading.domain.Account;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Credits.Credits;

public class AccountAssembler {
    public static Account create(AccountPostRequestDTO accountPostRequestDto) {

        Credits credits = new Credits(accountPostRequestDto.credits);
        Credits nullCredits = new Credits();
        if (credits.compareTo(nullCredits) <= 0) {
            throw new InvalidCreditsAmountException();
        }

        return new Account(
                accountPostRequestDto.investorId,
                accountPostRequestDto.investorName,
                new Credits(accountPostRequestDto.credits)
        );
    }
}

