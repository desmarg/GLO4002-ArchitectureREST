package trading.persistence;

import trading.domain.Credits;
import trading.domain.Currency;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;
import trading.domain.transaction.*;

import java.sql.Timestamp;

public class TransactionHydrator {
    public static TransactionHibernateDTO toHibernateDto(Transaction transaction) {
        TransactionHibernateDTO transactionHibernateDTO = new TransactionHibernateDTO();
        transactionHibernateDTO.transactionNumber = transaction.getTransactionID().getId();
        transactionHibernateDTO.accountNumber = transaction.getAccountNumber().getString();
        transactionHibernateDTO.transactionType = transaction.getTransactionType().toString();
        transactionHibernateDTO.quantity = transaction.getQuantity();
        transactionHibernateDTO.instant = Timestamp.from(transaction.getDateTime().toInstant());
        transactionHibernateDTO.market = transaction.getStock().getMarket();
        transactionHibernateDTO.symbol = transaction.getStock().getSymbol();
        transactionHibernateDTO.stockPrice = transaction.getStockPrice().getAmount();
        transactionHibernateDTO.stockCurrency = transaction.getStockPrice().getCurrency().toString();
        transactionHibernateDTO.referredTransactionNumber = null;

        if (transaction instanceof TransactionSell) {
            transactionHibernateDTO.referredTransactionNumber =
                    ((TransactionSell) transaction).getReferredTransactionID().getStringUUID();
        }

        return transactionHibernateDTO;
    }

    public static Transaction toTransaction(TransactionHibernateDTO transactionHibernateDTO) {
        AccountNumber accountNumber = new AccountNumber(transactionHibernateDTO.accountNumber);
        TransactionID transactionID =
                new TransactionID(transactionHibernateDTO.transactionNumber);
        TransactionType transactionType =
                TransactionType.valueOf(transactionHibernateDTO.transactionType);
        Long quantity = transactionHibernateDTO.quantity;
        DateTime dateTime = new DateTime(transactionHibernateDTO.instant.toInstant());
        Stock stock = new Stock(transactionHibernateDTO.market, transactionHibernateDTO.symbol);
        Credits stockPrice = new Credits(transactionHibernateDTO.stockPrice, Currency.valueOf(transactionHibernateDTO.stockCurrency));

        if (transactionType.equals(TransactionType.SELL)) {
            TransactionID referredTransactionID =
                    new TransactionID(transactionHibernateDTO.referredTransactionNumber);
            return new TransactionSell(quantity, dateTime, stock, stockPrice,
                    referredTransactionID, accountNumber, transactionID);
        } else {
            return new TransactionBuy(quantity, dateTime, stock, stockPrice, accountNumber,
                    transactionID);
        }
    }

    public static TransactionBuy toTransactionBuy(TransactionHibernateDTO transactionHibernateDTO) {
        if (!transactionHibernateDTO.transactionType.equals(TransactionType.BUY.toString())) {
            throw new InvalidTransactionNumberException();
        }
        AccountNumber accountNumber = new AccountNumber(transactionHibernateDTO.accountNumber);
        TransactionID transactionID =
                new TransactionID(transactionHibernateDTO.transactionNumber);
        Long quantity = transactionHibernateDTO.quantity;
        DateTime dateTime = new DateTime(transactionHibernateDTO.instant.toInstant());
        Stock stock = new Stock(transactionHibernateDTO.market, transactionHibernateDTO.symbol);
        Credits stockPrice = new Credits(transactionHibernateDTO.stockPrice, Currency.valueOf(transactionHibernateDTO.stockCurrency));

        return new TransactionBuy(quantity, dateTime, stock, stockPrice, accountNumber,
                transactionID);
    }

    public static TransactionSell toTransactionSell(
            TransactionHibernateDTO transactionHibernateDTO
    ) {
        if (!transactionHibernateDTO.transactionType.equals(TransactionType.SELL.toString())) {
            throw new InvalidTransactionNumberException();
        }
        AccountNumber accountNumber = new AccountNumber(transactionHibernateDTO.accountNumber);
        TransactionID transactionID =
                new TransactionID(transactionHibernateDTO.transactionNumber);
        Long quantity = transactionHibernateDTO.quantity;
        DateTime dateTime = new DateTime(transactionHibernateDTO.instant.toInstant());
        Stock stock = new Stock(transactionHibernateDTO.market, transactionHibernateDTO.symbol);
        Credits stockPrice = new Credits(transactionHibernateDTO.stockPrice, Currency.valueOf(transactionHibernateDTO.stockCurrency));
        TransactionID referredTransactionID =
                new TransactionID(transactionHibernateDTO.referredTransactionNumber);
        return new TransactionSell(quantity, dateTime, stock, stockPrice,
                referredTransactionID, accountNumber, transactionID);
    }
}
