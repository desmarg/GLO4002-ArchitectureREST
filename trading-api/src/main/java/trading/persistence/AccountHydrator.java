package trading.persistence;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;
import trading.domain.account.Account;
import trading.domain.account.AccountNumber;
import trading.domain.transaction.TransactionID;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountHydrator {

    public static Account toAccount(AccountHibernateDTO accountHibernateDTO) {
        InvestorProfile investorProfile =
                new InvestorProfile(ProfileType.valueOf(accountHibernateDTO.profileType),
                        accountHibernateDTO.focusAreas);
        Map<TransactionID, Long> remainingStocksMap =
                AccountHydrator.hydrateRemainingStocksMap(accountHibernateDTO.remainingStocksMap);
        Long investorId = accountHibernateDTO.investorId;
        String investorName = accountHibernateDTO.investorName;
        HashMap<Currency, Credits> credits = hydrateCreditMap(accountHibernateDTO.creditList);
        HashMap<Currency, Credits> initialCredits = hydrateCreditMap(accountHibernateDTO.initialCredits);
        AccountNumber accountNumber = new AccountNumber(accountHibernateDTO.accountNumber);

        return new Account(investorId, investorName, credits, initialCredits, investorProfile,
                remainingStocksMap, accountNumber);
    }

    public static AccountHibernateDTO toHibernateDto(Account account) {
        AccountHibernateDTO accountHibernateDTO = new AccountHibernateDTO();
        accountHibernateDTO.accountNumber = account.getAccountNumber().getString();
        accountHibernateDTO.investorId = account.getInvestorId();
        accountHibernateDTO.profileType = account.getInvestorProfile().getType().toString();
        accountHibernateDTO.focusAreas = account.getInvestorProfile().getFocusAreas();
        accountHibernateDTO.investorName = account.getInvestorName();
        accountHibernateDTO.creditList = dehydrateCreditMap(account.getCredits());
        accountHibernateDTO.initialCredits = dehydrateCreditMap(account.getInitialCredits());
        accountHibernateDTO.remainingStocksMap =
                AccountHydrator.dehydrateRemainingStocksMap(account.getRemainingStocksMap());

        return accountHibernateDTO;
    }

    private static Map<String, BigDecimal> dehydrateCreditMap(HashMap<Currency, Credits> creditMap) {
        Map<String, BigDecimal> dehydratedCredits = new HashMap<>();

        for (Currency currency : creditMap.keySet()) {
            dehydratedCredits.merge(currency.toString(), creditMap.get(currency).getAmount(), BigDecimal::add);
        }
        return dehydratedCredits;
    }

    private static HashMap<Currency, Credits> hydrateCreditMap(Map<String, BigDecimal> dehydratedCredits) {
        HashMap<Currency, Credits> hydratedCredits = new HashMap<>();

        for (String currency : dehydratedCredits.keySet()) {
            BigDecimal amount = dehydratedCredits.get(currency);
            hydratedCredits.put(Currency.valueOf(currency), new Credits(amount, Currency.valueOf(currency)));
        }
        return hydratedCredits;
    }

    private static Map<UUID, Long> dehydrateRemainingStocksMap(
            Map<TransactionID, Long> remainingStocksMap
    ) {
        Map<UUID, Long> modifiedRemainingStocksMap = new HashMap<>();

        for (Map.Entry<TransactionID, Long> entry : remainingStocksMap.entrySet()) {
            UUID transactionUUID = entry.getKey().getId();
            Long remainingStocks = entry.getValue();
            modifiedRemainingStocksMap.put(transactionUUID, remainingStocks);
        }

        return modifiedRemainingStocksMap;
    }

    private static Map<TransactionID, Long> hydrateRemainingStocksMap(
            Map<UUID, Long> remainingStocksMap
    ) {
        Map<TransactionID, Long> modifiedRemainingStocksMap = new HashMap<>();

        for (Map.Entry<UUID, Long> entry : remainingStocksMap.entrySet()) {
            TransactionID transactionID = new TransactionID(entry.getKey());
            Long remainingStocks = entry.getValue();

            modifiedRemainingStocksMap.put(transactionID, remainingStocks);
        }

        return modifiedRemainingStocksMap;
    }
}
