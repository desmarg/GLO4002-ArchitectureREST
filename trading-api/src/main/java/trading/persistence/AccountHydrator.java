package trading.persistence;

import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;
import trading.domain.transaction.TransactionNumber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountHydrator {

    public static Account toAccount(AccountHibernateDTO accountHibernateDTO){
        InvestorProfile investorProfile = new InvestorProfile(ProfileType.valueOf(accountHibernateDTO.profileType), accountHibernateDTO.focusAreas);
        Map<TransactionNumber, Long> modifiedRemainingStocksMap = AccountHydrator.hydrateRemainingStocksMap(accountHibernateDTO.remainingStocksMap);
        Long investorId = accountHibernateDTO.investorId;
        String investorName = accountHibernateDTO.investorName;
        Credits credits = new Credits(accountHibernateDTO.credits);
        AccountNumber accountNumber = new AccountNumber(accountHibernateDTO.accountNumber);

        Account account = new Account(investorId, investorName, credits, accountNumber, investorProfile, modifiedRemainingStocksMap);

        return account;
    }

    public static AccountHibernateDTO toHibernateDto(Account account) {
        AccountHibernateDTO accountHibernateDTO = new AccountHibernateDTO();
        accountHibernateDTO.accountNumber = account.getAccountNumber().getId();
        accountHibernateDTO.investorId = account.getInvestorId();
        accountHibernateDTO.profileType = account.getInvestorProfile().getProfileType().toString();
        accountHibernateDTO.focusAreas = account.getInvestorProfile().getFocusAreas();
        accountHibernateDTO.investorName = account.getInvestorName();
        accountHibernateDTO.credits = account.getCredits().getAmount();
        accountHibernateDTO.remainingStocksMap = AccountHydrator.dehydrateRemainingStocksMap(account.getRemainingStocksMap());

        return accountHibernateDTO;
    }

    private static Map<UUID, Long> dehydrateRemainingStocksMap(Map<TransactionNumber, Long> remainingStocksMap) {
        Map<UUID, Long> modifiedRemainingStocksMap = new HashMap<>();

        for (Map.Entry<TransactionNumber, Long> entry : remainingStocksMap.entrySet()) {
            UUID transactionUUID = entry.getKey().getId();
            Long remainingStocks = entry.getValue();
            modifiedRemainingStocksMap.put(transactionUUID, remainingStocks);
        }

        return modifiedRemainingStocksMap;
    }

    private static Map<TransactionNumber, Long> hydrateRemainingStocksMap(Map<UUID, Long> remainingStocksMap) {
        Map<TransactionNumber, Long> modifiedRemainingStocksMap = new HashMap<>();

        for (Map.Entry<UUID, Long> entry : remainingStocksMap.entrySet()) {
            TransactionNumber transactionNumber = new TransactionNumber(entry.getKey());
            Long remainingStocks = entry.getValue();

            modifiedRemainingStocksMap.put(transactionNumber, remainingStocks);
        }

        return modifiedRemainingStocksMap;
    }
}
