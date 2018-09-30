package api.account;

import domain.Credits;
import domain.account.Account;
import domain.account.AccountNumber;

public class AccountPostDtoToAccountAssembler {

    public static Account createAccount(AccountPostDto accountPostDto, long accountNumber){
        return new Account(
                accountPostDto.getInvestorId(),
                accountPostDto.getInvestorName(),
                accountPostDto.getEmail(),
                Credits.fromFloat(accountPostDto.getCredits()),
                new AccountNumber(accountNumber)
        );
        }
}

//if (investorId == null) {
//        throw new InvalidParameterException("investorId cannot be null");
//        } else if (investorId < 0) {
//        throw new InvalidParameterException("investorId cannot be negative");
//        }
//
//        if (investorName == null) {
//        throw new InvalidParameterException("investorName cannot be null");
//        } else if (investorName.isEmpty()) {
//        throw new InvalidParameterException("investorName cannot be empty");
//        }
//
//        if (email == null) {
//        throw new InvalidParameterException("email cannot be null");
//        } else if (email.isEmpty()) {
//        throw new InvalidParameterException("email cannot be empty");
//        }
//
//        if (credits == null) {
//        throw new InvalidParameterException("credits cannot be null");
//        } else if (credits.compareTo(Credits.fromFloat(0.00)) <= 0) {
//        throw new InvalidCreditsAmountException();
//        }
//
//        if (accountNumber == null) {
//        throw new InvalidParameterException("accountNumber cannot be null");
//        } else if (accountNumber.getId() < 0) {
//        throw new InvalidParameterException("accountNumber cannot be negative");
//        }
