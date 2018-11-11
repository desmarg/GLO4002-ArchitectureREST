package trading.domain.Wallet;

import trading.domain.Account.AccountNumber;

import java.time.Instant;

public interface WalletRepository {

    Wallet save(Wallet wallet);

    Wallet findByAccountNumberAndDate(AccountNumber accountNumber, Instant date);

    Wallet findMostRecentWalletFromDate(AccountNumber accountNumber, Instant date);
}
