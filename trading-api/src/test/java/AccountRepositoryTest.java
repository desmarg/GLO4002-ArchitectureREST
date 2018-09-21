import models.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.AccountRepository;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    private AccountRepository accountRepository = AccountRepository.getInstance();
    private Account newAccount;
    private Long SOME_ACCOUNT_NUMBER = 123L;
    private Long NON_EXISTENT_ACCOUNT_NUMBER = 999L;
    private String SOME_NAME = "I am a name xDDDDD";

    @Before
    public void saveNewAccount() {
        this.newAccount = new Account(SOME_ACCOUNT_NUMBER);
        this.accountRepository.save(newAccount);
    }

    @After
    public void deleteNewAccount() {
        this.accountRepository.delete(SOME_ACCOUNT_NUMBER);
    }

    @Test
    public void givenOneAccountSaved_whenRetrieveByAccountID_thenAccountCanBeRetrieved() {
        Account retrievedAccount = accountRepository.fetchOne(SOME_ACCOUNT_NUMBER);

        assertEquals(this.newAccount, retrievedAccount);
    }

    @Test
    public void whenSavingAnExistingAccount_accountGetsUpdated() {
        this.newAccount.setName(SOME_NAME);
        this.accountRepository.save(this.newAccount);
        Account retrievedAccount = accountRepository.fetchOne(SOME_ACCOUNT_NUMBER);

        assertEquals(this.newAccount, retrievedAccount);
    }

    @Test
    public void givenSavedAccount_whenAccountDeleted_thenCannotRetrieveAccount() {
        this.accountRepository.delete(SOME_ACCOUNT_NUMBER);
        Account retrievedAccount = accountRepository.fetchOne(SOME_ACCOUNT_NUMBER);

        assertNull(retrievedAccount);
    }

    @Test
    public void whenFetchingNonExistingAccount_thenDoesNotFetchAnything() {
        Account retrievedAccount = this.accountRepository.fetchOne(NON_EXISTENT_ACCOUNT_NUMBER);

        assertNull(retrievedAccount);
    }
}