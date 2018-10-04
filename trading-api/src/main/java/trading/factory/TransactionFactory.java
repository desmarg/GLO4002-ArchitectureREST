package trading.factory;

import trading.api.request.TransactionPostRequest;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.domain.Transaction;
import trading.domain.TransactionBuy;
import trading.domain.TransactionNumber;
import trading.domain.TransactionSell;
import trading.domain.TransactionType;
import trading.exception.UnsupportedTransactionTypeException;
import trading.services.StockService;

public class TransactionFactory {

    public static Transaction create(TransactionPostRequest transactionRequest) {

    	TransactionType transactionType = TransactionType.valueOf(transactionRequest.getType());

        if (transactionType == TransactionType.SELL) {
            return TransactionSell.fromRequest(transactionRequest);
        } else if (transactionType == TransactionType.BUY) {
            return TransactionBuy.fromRequest(transactionRequest);
        }
        throw new UnsupportedTransactionTypeException(transactionType);
    }
}
