package api.account;

import api.response.AccountResponse;
import domain.Account;

public class AccountToAccountGetDtoAssembler {

    public static AccountResponse makeGetAccountDto(Account account) {

        return new AccountResponse(account);
    }

}
