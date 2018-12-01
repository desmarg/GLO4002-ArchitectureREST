package trading.domain.account;

public interface AccountRepository {
    void save(Account account);

    void update(Account account);

    Account findByAccountNumber(AccountNumber accountNumber);

    Integer getNumberOfAccounts();
}
