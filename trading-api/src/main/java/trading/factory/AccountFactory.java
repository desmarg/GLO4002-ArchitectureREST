package trading.factory;

import trading.api.request.AccountPostRequest;
import trading.domain.Account;

public class AccountFactory {
    public static Account create(AccountPostRequest accountPostRequest) {

        return new Account(
                accountPostRequest.investorId,
                accountPostRequest.investorName,
                accountPostRequest.email,
                accountPostRequest.credits
        );
    }
}
