package api.account;

import domain.account.Account;

public class AccountToAccountGetDtoAssembler {

    public static AccountGetDto makeGetAccountDto(Account account){
        AccountGetDto accountGetDto = new AccountGetDto();
        accountGetDto.setAccountNumber(account.getLongAccountNumber());
        accountGetDto.setInvestorProfile(account.getInvestorProfile());
        return accountGetDto;
    }

}
