package trading.domain.Account;

public interface AccountRepository {
    Long save(Account account);

    Account findByAccountId(Long accountNumber);

    boolean accountAlreadyExists(Long investorId);
}
