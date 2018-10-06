package trading.factory;

import trading.api.request.AccountPostRequest;
import trading.domain.Account;
import trading.domain.Credits;

public class AccountFactory {

    public static Account create(AccountPostRequest accountPostRequest) {
        NullPointerGuard.validate(accountPostRequest);
        return new Account(
                accountPostRequest.getInvestorId(),
                accountPostRequest.getInvestorName(),
                accountPostRequest.getEmail(),
                Credits.fromDouble(accountPostRequest.getCredits())
        );
    }
}
