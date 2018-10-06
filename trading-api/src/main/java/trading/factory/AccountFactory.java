package trading.factory;

import trading.api.request.AccountPostRequest;
import trading.domain.Account;

public class AccountFactory {

    public static Account create(AccountPostRequest accountPostDto) {
        NullPointerGuard.validate(accountPostDto);
        return Account.fromRequest(accountPostDto);
    }
}
