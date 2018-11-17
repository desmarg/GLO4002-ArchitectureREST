package trading.persistence;

import trading.domain.account.AccountNumber;
import trading.domain.Credits;
import trading.domain.datetime.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.*;

import java.sql.Timestamp;

public class TransactionHydrator {
    public static TransactionHibernateDTO toHibernateDto(Transaction transaction) {
        TransactionHibernateDTO transactionHibernateDTO = new TransactionHibernateDTO();
        transactionHibernateDTO.transactionNumber = transaction.getTransactionNumber().getId();
        transactionHibernateDTO.accountNumber = transaction.getAccountNumber().getString();
        transactionHibernateDTO.transactionType = transaction.getTransactionType().toString();
        transactionHibernateDTO.quantity = transaction.getQuantity();
        transactionHibernateDTO.instant = Timestamp.from(transaction.getDateTime().toInstant());
        transactionHibernateDTO.market = transaction.getStock().getMarket();
        transactionHibernateDTO.symbol = transaction.getStock().getSymbol();
        transactionHibernateDTO.stockPrice = transaction.getStockPrice().toBigDecimal();
        transactionHibernateDTO.referredTransactionNumber = null;

        if (transaction instanceof TransactionSell) {
            transactionHibernateDTO.referredTransactionNumber = ((TransactionSell) transaction)
                    .getReferredTransactionNumber().getStringUUID();
        }

        return transactionHibernateDTO;
    }

    public static Transaction toTransaction(TransactionHibernateDTO transactionHibernateDTO) {
        AccountNumber accountNumber = new AccountNumber(transactionHibernateDTO.accountNumber);
        TransactionNumber transactionNumber = new TransactionNumber(transactionHibernateDTO.transactionNumber);
        TransactionType transactionType = TransactionType.valueOf(transactionHibernateDTO
                .transactionType);
        Long quantity = transactionHibernateDTO.quantity;
        DateTime dateTime = new DateTime(transactionHibernateDTO.instant.toInstant());
        Stock stock = new Stock(transactionHibernateDTO.market, transactionHibernateDTO.symbol);
        Credits stockPrice = new Credits(transactionHibernateDTO.stockPrice);

        if (transactionType.equals(TransactionType.SELL)) {
            TransactionNumber referredTransactionNumber = new TransactionNumber
                    (transactionHibernateDTO.referredTransactionNumber);
            return new TransactionSell(
                    quantity,
                    dateTime,
                    stock,
                    stockPrice,
                    referredTransactionNumber,
                    accountNumber,
                    transactionNumber
            );
        } else {
            return new TransactionBuy(
                    quantity,
                    dateTime,
                    stock,
                    stockPrice,
                    accountNumber,
                    transactionNumber
            );
        }
    }

    public static TransactionBuy toTransactionBuy(TransactionHibernateDTO transactionHibernateDTO) {
        if (transactionHibernateDTO.transactionType != TransactionType.BUY.toString()) {
            throw new InvalidTransactionNumberException(new TransactionNumber(transactionHibernateDTO.transactionNumber));
        }
        AccountNumber accountNumber = new AccountNumber(transactionHibernateDTO.accountNumber);
        TransactionNumber transactionNumber = new TransactionNumber(transactionHibernateDTO.transactionNumber);
        Long quantity = transactionHibernateDTO.quantity;
        DateTime dateTime = new DateTime(transactionHibernateDTO.instant.toInstant());
        Stock stock = new Stock(transactionHibernateDTO.market, transactionHibernateDTO.symbol);
        Credits stockPrice = new Credits(transactionHibernateDTO.stockPrice);

        return new TransactionBuy(
                quantity,
                dateTime,
                stock,
                stockPrice,
                accountNumber,
                transactionNumber
        );
    }

    public static TransactionSell toTransactionSell(TransactionHibernateDTO transactionHibernateDTO) {
        if (transactionHibernateDTO.transactionType != TransactionType.SELL.toString()) {
            throw new InvalidTransactionNumberException(new TransactionNumber(transactionHibernateDTO.transactionNumber));
        }
        AccountNumber accountNumber = new AccountNumber(transactionHibernateDTO.accountNumber);
        TransactionNumber transactionNumber = new TransactionNumber(transactionHibernateDTO.transactionNumber);
        Long quantity = transactionHibernateDTO.quantity;
        DateTime dateTime = new DateTime(transactionHibernateDTO.instant.toInstant());
        Stock stock = new Stock(transactionHibernateDTO.market, transactionHibernateDTO.symbol);
        Credits stockPrice = new Credits(transactionHibernateDTO.stockPrice);
        TransactionNumber referredTransactionNumber = new TransactionNumber
                (transactionHibernateDTO.referredTransactionNumber);
        return new TransactionSell(
                quantity,
                dateTime,
                stock,
                stockPrice,
                referredTransactionNumber,
                accountNumber,
                transactionNumber
        );
    }
}
