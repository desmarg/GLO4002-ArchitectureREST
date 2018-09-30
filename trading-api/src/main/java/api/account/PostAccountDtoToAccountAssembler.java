package api.account;

import domain.Credits;
import domain.account.Account;
import domain.account.AccountNumber;

public class PostAccountDtoToAccountAssembler {

    public static Account createAccount(PostAccountDto postAccountDto, long accountNumber){
        return new Account(
                postAccountDto.getInvestorId(),
                postAccountDto.getInvestorName(),
                postAccountDto.getEmail(),
                Credits.fromFloat(postAccountDto.getCredits()),
                new AccountNumber(accountNumber)
        );
        }
}
