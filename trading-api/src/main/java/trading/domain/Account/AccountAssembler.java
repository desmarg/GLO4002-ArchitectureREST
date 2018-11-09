package trading.domain.Account;

import trading.api.request.AccountPostRequest;

public class AccountAssembler {
    public static Account create(AccountPostRequest accountPostRequest) {

        return new Account(
                accountPostRequest.investorId,
                accountPostRequest.investorName,
                accountPostRequest.email,
                accountPostRequest.credits
        );
    }
}
