package trading.persistence;

import trading.domain.Account.AccountNumber;
import trading.domain.DateTime.InvalidDateException;
import trading.domain.Wallet.Wallet;
import trading.domain.Wallet.WalletRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class WalletRepositoryInMemory implements WalletRepository {

    private final List<Wallet> wallets = new ArrayList<Wallet>();

    public Wallet save(Wallet wallet) {
        for (Wallet walletInList : this.wallets) {
            if (wallet.getDate() == walletInList.getDate() && wallet.getAccountNumber() == walletInList.getAccountNumber()) {
                this.wallets.remove(walletInList);
                this.wallets.add(wallet);
                return wallet;

            }
        }
        this.wallets.add(wallet);
        return wallet;
    }

    public Wallet findByAccountNumberAndDate(AccountNumber accountNumber, Instant date) {
        for (Wallet walletInList : this.wallets) {
            if (date == walletInList.getDate() && accountNumber == walletInList.getAccountNumber()) {
                return walletInList;
            }
        }
        throw new InvalidDateException();
    }

    public Wallet findMostRecentWalletFromDate(AccountNumber accountNumber, Instant date) {
        Wallet mostRecentWallet = null;
        for (Wallet walletInList : this.wallets) {
            if (accountNumber == walletInList.getAccountNumber()) {
                if (walletInList.getDate().compareTo(date) == 0) {
                    return walletInList;
                }
                if (walletInList.getDate().compareTo(date) < 0) {
                    if (mostRecentWallet == null) {
                        mostRecentWallet = walletInList;
                    } else if (walletInList.getDate().compareTo(mostRecentWallet.getDate()) > 0) {
                        mostRecentWallet = walletInList;
                    }
                }
            }
        }
        if (mostRecentWallet != null) {
            return mostRecentWallet;
        }
        throw new InvalidDateException();
    }
}
