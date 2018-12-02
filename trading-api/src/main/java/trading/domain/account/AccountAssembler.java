package trading.domain.account;

import trading.api.request.AccountPostRequestDTO;
import trading.api.request.CreditsDTO;
import trading.api.response.AccountResponseDTO;
import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.ForeignExchangeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AccountAssembler {
    public static Account create(AccountPostRequestDTO accountPostRequestDto, Integer id) {
        return new Account(accountPostRequestDto.investorId, accountPostRequestDto.investorName,
                createCreditsList(accountPostRequestDto.credits), id);
    }

    public static AccountResponseDTO toAccountResponseDTO(Account account, ForeignExchangeRepository forexRepo) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.investorProfile = account.getInvestorProfile();
        accountResponseDTO.accountNumber = account.getAccountNumber().getString();
        accountResponseDTO.investorId = account.getInvestorId();
        accountResponseDTO.credits = account.getCredits();
        accountResponseDTO.total = account.getTotalCreditsInCAD(forexRepo);

        return accountResponseDTO;
    }

    private static ArrayList<Credits> createCreditsList(ArrayList<CreditsDTO> creditsDTOs) {
        ArrayList<Credits> creditList = new ArrayList<>();
        for (CreditsDTO creditsDTO : creditsDTOs) {
            if (creditsDTO.amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidCreditsAmountException();
            }
            try {
                Credits credits = new Credits(creditsDTO.amount, Currency.valueOf(creditsDTO.currency));
                creditList.add(credits);
            } catch(IllegalArgumentException e) { }
        }
        return creditList;
    }
}

