package trading.services;

import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.Transaction.TransactionBuy;
import trading.domain.Transaction.TransactionNumber;
import trading.domain.Wallet.TransactionSnapshot;
import trading.domain.Wallet.Wallet;
import trading.domain.Wallet.WalletRepository;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet save(Account account, Instant date) {
        Map<TransactionNumber, TransactionSnapshot> transactionSnapshotsMap = new HashMap<>();
        Wallet wallet = new Wallet(account.getAccountNumber(), date, account.getCredits(), transactionSnapshotsMap);
        this.walletRepository.save(wallet);
        return wallet;
    }

    public void update(Account account, TransactionBuy transactionBuy) {
        Instant transactionDate = transactionBuy.getDateTime().getInstantDate();
        Wallet mostRecentWallet = this.getMostRecentWallet(account.getAccountNumber(), transactionDate);
        TransactionSnapshot transactionSnapshot = this.createTransactionSnaphot(transactionBuy);
        mostRecentWallet.setDate(transactionDate);
        mostRecentWallet.append(transactionSnapshot);
        mostRecentWallet.setCredits(account.getCredits());
        this.walletRepository.save(mostRecentWallet);
    }

    private Wallet getMostRecentWallet(AccountNumber accountNumber, Instant date) {
        return this.walletRepository.findMostRecentWalletFromDate(accountNumber, date);
    }

    private TransactionSnapshot createTransactionSnaphot(TransactionBuy transactionBuy) {
        return new TransactionSnapshot(transactionBuy);
    }

}
