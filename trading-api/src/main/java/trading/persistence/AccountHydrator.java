package trading.persistence;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.InvestorProfile;
import trading.domain.ProfileType;
import trading.domain.account.Account;
import trading.domain.account.AccountNumber;
import trading.domain.transaction.TransactionNumber;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountHydrator {

    public static Account toAccount(AccountHibernateDTO accountHibernateDTO) {
        InvestorProfile investorProfile =
                new InvestorProfile(ProfileType.valueOf(accountHibernateDTO.profileType),
                        accountHibernateDTO.focusAreas);
        Map<TransactionNumber, Long> remainingStocksMap =
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
            Map<TransactionNumber, Long> remainingStocksMap
    ) {
        Map<UUID, Long> modifiedRemainingStocksMap = new HashMap<>();

        for (Map.Entry<TransactionNumber, Long> entry : remainingStocksMap.entrySet()) {
            UUID transactionUUID = entry.getKey().getId();
            Long remainingStocks = entry.getValue();
            modifiedRemainingStocksMap.put(transactionUUID, remainingStocks);
        }

        return modifiedRemainingStocksMap;
    }

    private static Map<TransactionNumber, Long> hydrateRemainingStocksMap(
            Map<UUID, Long> remainingStocksMap
    ) {
        Map<TransactionNumber, Long> modifiedRemainingStocksMap = new HashMap<>();

        for (Map.Entry<UUID, Long> entry : remainingStocksMap.entrySet()) {
            TransactionNumber transactionNumber = new TransactionNumber(entry.getKey());
            Long remainingStocks = entry.getValue();

            modifiedRemainingStocksMap.put(transactionNumber, remainingStocks);
        }

        return modifiedRemainingStocksMap;
    }
}
