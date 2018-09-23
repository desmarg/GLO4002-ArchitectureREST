package api.account;

import domain.Account;

public class AccountAssembler {

    public Account toEntity(AccountDTO accountDTO){
        Account newAccount = new Account(   accountDTO.getInvestorId(),
                                            accountDTO.getInvestorName(),
                                            accountDTO.getEmail(),
                                            accountDTO.getCredits()
        );
        return newAccount;
    }

    public AccountDTO toDTO(Account account){
        AccountDTO newAccountDTO = new AccountDTO();
        newAccountDTO.setInvestorId(account.getInvestorId());
        newAccountDTO.setInvestorName(account.getInvestorName());
        newAccountDTO.setEmail(account.getEmail());
        newAccountDTO.setCredits(account.getCredits());
        return newAccountDTO;
    }
}
