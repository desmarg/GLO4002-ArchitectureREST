package api.account;

import domain.account.Account;

public class AccountToAccountGetDtoAssembler {

    public static AccountGetDto makeGetAccountDto(Account account) {

        return new AccountGetDto(account);
    }

}
