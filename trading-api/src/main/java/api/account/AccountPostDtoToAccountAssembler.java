package api.account;

import domain.Credits;
import domain.account.Account;
import domain.account.AccountNumber;

public class AccountPostDtoToAccountAssembler {

    public static Account createAccount(AccountPostDto accountPostDto, long accountNumber){
        return new Account(
                accountPostDto.getInvestorId(),
                accountPostDto.getInvestorName(),
                accountPostDto.getEmail(),
                Credits.fromDouble(accountPostDto.getCredits()),
                new AccountNumber(accountNumber)
        );
        }
}