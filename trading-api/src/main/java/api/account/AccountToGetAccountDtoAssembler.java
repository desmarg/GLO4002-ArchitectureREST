package api.account;

import domain.account.Account;

public class AccountToGetAccountDtoAssembler {

    public static GetAccountDto makeGetAccountDto(Account account){
        GetAccountDto getAccountDto = new GetAccountDto();
        getAccountDto.setAccountNumber(account.getLongAccountNumber());
        getAccountDto.setInvestorProfile(account.getInvestorProfile());
        return getAccountDto;
    }

}
