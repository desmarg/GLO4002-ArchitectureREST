package trading.domain.Account;

import trading.api.request.AccountPostRequestDTO;
import trading.domain.Credits;

public class AccountAssembler {
    public static Account create(AccountPostRequestDTO accountPostRequestDto, int id) {

        Credits credits = new Credits(accountPostRequestDto.credits);
        Credits nullCredits = Credits.ZERO;
        if (credits.isSmallerOrEqual(nullCredits)) {
            throw new InvalidCreditsAmountException();
        }

        return new Account(
                accountPostRequestDto.investorId,
                accountPostRequestDto.investorName,
                new Credits(accountPostRequestDto.credits),
                id
        );
    }
}

