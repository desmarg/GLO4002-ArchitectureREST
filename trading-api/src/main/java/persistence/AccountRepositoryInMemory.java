package persistence;

import api.account.AccountCreatorDto;
import domain.Account;
import domain.investorprofile.InvestorProfile;
import domain.investorprofile.ProfileType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepositoryInMemory implements AccountRepository {

    private Map<Long, Account> accountMap = new HashMap<>();
    private static Long ACCOUNT_NUMBER_COUNTER = 0L;

    public Account add(AccountCreatorDto accountCreatorDto) {
        // We use a temporary value here in case account's constructor
        // throws.
        Long tempAccountNumber = this.ACCOUNT_NUMBER_COUNTER + 1;
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
        this.accountMap.put(account.getInvestorId(), account);
        return account;
    }

    public Account findByAccountNumber(Long accountNumber) throws AccountNotFoundException {
        for (Account account : this.accountMap.values()) {
            if (accountNumber == account.getAccountNumber()) {
                return account;
            }
        }
        throw new AccountNotFoundException(accountNumber);
    }

    public boolean checkIfAccountExists(Long investorId) {
        return this.accountMap.containsKey(investorId);
    }

    public Long getCounterValue() {
        return this.ACCOUNT_NUMBER_COUNTER;
    }
}
