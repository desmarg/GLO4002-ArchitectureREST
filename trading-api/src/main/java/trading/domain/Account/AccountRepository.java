package trading.domain.Account;

public interface AccountRepository {
    void save(Account account);

    void update(Account account);

    Account findByAccountNumber(AccountNumber accountNumber);

    boolean accountAlreadyExists(Long investorId);

    Long getCurrentAccountNumber();
}
