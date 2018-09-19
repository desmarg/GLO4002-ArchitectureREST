import models.Account;
import org.junit.Before;
import org.junit.Test;
import repositories.AccountRepository;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    AccountRepository accountRepository = AccountRepository.getInstance();

    @Test
    public void whenAddingAnAccount_accountCanBeRetrieved() {
        Long accountNumber = new Long(123);
        Account newAccount = new Account(accountNumber);

        accountRepository.save(newAccount);
        Account retrievedAccount = accountRepository.fetchOne(accountNumber);

        assertEquals(newAccount, retrievedAccount);
    }
}