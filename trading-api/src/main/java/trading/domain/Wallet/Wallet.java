package trading.domain.Wallet;

import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.Transaction.TransactionNumber;

import java.time.Instant;
import java.util.Map;

public class Wallet {

    private final AccountNumber accountNumber;
    private Instant date;
    private Credits creditsInAccount;
    private final Map<TransactionNumber, TransactionSnapshot> transactionSnapshotsMap;

    public Wallet(AccountNumber accountNumber,
                  Instant date,
                  Credits creditsInAccount,
                  Map<TransactionNumber, TransactionSnapshot> transactionSnapshotsMap) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.creditsInAccount = creditsInAccount;
        this.transactionSnapshotsMap = transactionSnapshotsMap;
    }

    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    public Instant getDate() {
        return this.date;
    }

    public void append(TransactionSnapshot transactionSnapshot) {
        this.transactionSnapshotsMap.put(transactionSnapshot.transactionBuyNumber, transactionSnapshot);
    }

    public void setCredits(Credits creditsInAccount) {
        this.creditsInAccount = creditsInAccount;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}

