package trading.domain.Account;

public interface AccountRepository {
    Account save(Account account);

    Account findByAccountNumber(AccountNumber accountNumber);

    boolean accountAlreadyExists(Long investorId);
}
