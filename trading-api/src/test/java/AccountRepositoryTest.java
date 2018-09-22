import models.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.AccountRepository;

import static org.junit.Assert.*;

public class AccountRepositoryTest {
    private AccountRepository accountRepository = AccountRepository.getInstance();
    private Account newAccount;
    private Long newAccountNumber;
    private String SOME_NAME = "I am a name!";

    @Before
    public void createAndSaveNewAccount() {
        this.newAccount = new Account();
        this.newAccountNumber = this.newAccount.getAccountNumber();
        this.accountRepository.save(this.newAccount);
    }

    @Test
    public void givenOneAccountSaved_whenRetrieveByAccountID_thenAccountCanBeRetrieved() {
        Account retrievedAccount = this.accountRepository.fetchOne(this.newAccountNumber);

        assertEquals(this.newAccount, retrievedAccount);
    }

    @Test
    public void whenSavingAnExistingAccount_accountGetsUpdated() {
        this.newAccount.setInvestorName(this.SOME_NAME);
        this.accountRepository.save(this.newAccount);
        Account retrievedAccount = accountRepository.fetchOne(this.newAccountNumber);

        assertEquals(this.newAccount, retrievedAccount);
    }

    @Test
    public void givenSavedAccount_whenAccountDeleted_thenCannotRetrieveAccount() {
        this.accountRepository.delete(this.newAccountNumber);
        Account retrievedAccount = accountRepository.fetchOne(this.newAccountNumber);

        assertNull(retrievedAccount);
    }

    @Test
    public void whenFetchingNonExistingAccount_thenDoesNotFetchAnything() {
        Account retrievedAccount = this.accountRepository.fetchOne(this.newAccountNumber + 1);

        assertNull(retrievedAccount);
    }
}