package persistence;

import api.account.AccountCreatorDto;
import domain.Account;
import domain.AccountNumber;
import domain.investorprofile.InvestorProfile;
import domain.investorprofile.ProfileType;
import exception.AccountNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {

    private Map<Long, AccountNumber> investorIdByAccountNumber = new HashMap<>();
    private Map<AccountNumber, Account> accountMap = new HashMap<>();
    private static Long ACCOUNT_NUMBER_COUNTER = 0L;

    public Account add(AccountCreatorDto accountCreatorDto) {
        // We use a temporary value here in case account's constructor
        // throws.
        Long tempAccountNumber = nextCounterValue();
        ProfileType profileType = ProfileType.CONSERVATIVE;
        List<String> focusAreas = new ArrayList<String>();
        InvestorProfile investorProfile = new InvestorProfile(profileType, focusAreas);
        Account account = new Account(
                accountCreatorDto.getInvestorId(),
                accountCreatorDto.getInvestorName(),
                accountCreatorDto.getEmail(),
                accountCreatorDto.getCredits(),
                tempAccountNumber,
                investorProfile
        );
        this.ACCOUNT_NUMBER_COUNTER++;
        this.investorIdByAccountNumber.put(account.getInvestorId(),account.getAccountNumber());
        this.accountMap.put(account.getAccountNumber(), account);
        return account;
    }

    public Account findByAccountNumber(AccountNumber accountNumber) throws AccountNotFoundException {
        Account account = this.accountMap.get(accountNumber);
        if(account != null){
            return account;
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public boolean checkIfAccountExists(Long investorId) {
        return this.investorIdByAccountNumber.containsKey(investorId);
    }

    public Long nextCounterValue() {
        return this.ACCOUNT_NUMBER_COUNTER + 1;
    }
}
