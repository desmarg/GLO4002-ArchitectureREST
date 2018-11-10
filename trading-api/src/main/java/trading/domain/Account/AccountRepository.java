package trading.domain.Account;

public interface AccountRepository {
    Long save(Account account);

    Account findByAccountNumber(Long accountNumber);

    boolean accountAlreadyExists(Long investorId);
}
