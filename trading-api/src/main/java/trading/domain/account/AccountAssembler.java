package trading.domain.account;

import trading.api.request.AccountPostRequestDTO;
import trading.api.request.CreditsDTO;
import trading.api.response.AccountResponseDTO;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.ForeignExchangeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountAssembler {
    public static Account create(AccountPostRequestDTO accountPostRequestDto, Integer id) {
        return new Account(accountPostRequestDto.investorId, accountPostRequestDto.investorName,
                createCreditMapFromList(accountPostRequestDto.credits), id);
    }

    public static AccountResponseDTO toAccountResponseDTO(Account account, ForeignExchangeRepository forexRepo) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.investorProfile = account.getInvestorProfile();
        accountResponseDTO.accountNumber = account.getAccountNumber().getString();
        accountResponseDTO.investorId = account.getInvestorId();
        accountResponseDTO.credits = createCreditListFromMap(account.getCredits());
        accountResponseDTO.total = account.getTotalCreditsInCAD(forexRepo).setScale(2, BigDecimal.ROUND_HALF_UP);

        return accountResponseDTO;
    }

    private static HashMap<Currency, Credits> createCreditMapFromList(ArrayList<CreditsDTO> creditsDTOs) {
        HashMap<Currency, Credits> creditMap = new HashMap<>();
        for (CreditsDTO creditsDTO : creditsDTOs) {
            if (creditsDTO.amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidCreditsAmountException();
            }
            try {
                Currency currency = Currency.valueOf(creditsDTO.currency);
                Credits credits = new Credits(creditsDTO.amount, currency);
                creditMap.merge(currency, credits, Credits::add);
            } catch(IllegalArgumentException e) { }
        }
        return creditMap;
    }

    private static ArrayList<Credits> createCreditListFromMap(HashMap<Currency, Credits> creditMap) {
        return new ArrayList<>(creditMap.values());
    }
}

