package api.account;

import api.request.AccountPostRequest;
import domain.Credits;
import domain.account.Account;
import domain.account.AccountNumber;

public class AccountPostDtoToAccountAssembler {

    public static Account createAccount(AccountPostRequest accountPostDto, long accountNumber) {
        return new Account(
                accountPostDto.getInvestorId(),
                accountPostDto.getInvestorName(),
                accountPostDto.getEmail(),
                Credits.fromDouble(accountPostDto.getCredits()),
                new AccountNumber(accountPostDto.getInvestorName(), accountNumber)
        );
    }
}