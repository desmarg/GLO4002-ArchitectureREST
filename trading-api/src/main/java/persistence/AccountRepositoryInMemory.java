package persistence;

import api.account.PostAccountDto;
import domain.investorprofile.InvestorProfile;
import domain.investorprofile.ProfileType;
import domain.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryInMemory implements AccountRepository {

    private Map<Long, Account> accountMap = new HashMap<>();
    private static Long ACCOUNT_NUMBER_COUNTER = 0L;

    public Account add(PostAccountDto postAccountDto) {
        // We use a temporary value here in case account's constructor
        // throws.
        Long tempAccountNumber = this.ACCOUNT_NUMBER_COUNTER + 1;
        ProfileType profileType = ProfileType.CONSERVATIVE;
        List<String> focusAreas = new ArrayList<String>();
        InvestorProfile investorProfile = new InvestorProfile(profileType, focusAreas);
        Account account = new Account(
                postAccountDto.getInvestorId(),
                postAccountDto.getInvestorName(),
                postAccountDto.getEmail(),
                postAccountDto.getCredits(),
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
