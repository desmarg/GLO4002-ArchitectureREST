package trading.factory;

import trading.api.request.TransactionPostRequest;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionBuy;
import trading.domain.transaction.TransactionSell;
import trading.domain.transaction.TransactionType;
import trading.exception.UnsupportedTransactionTypeException;

public class TransactionFactory {

    public static Transaction create(TransactionPostRequest transactionRequest) {

        NullPointerGuard.validate(transactionRequest);
        TransactionType transactionType = TransactionType.valueOf(transactionRequest.getType());

        if (transactionType == TransactionType.SELL) {
            return TransactionSell.fromRequest(transactionRequest);
        } else if (transactionType == TransactionType.BUY) {
            return TransactionBuy.fromRequest(transactionRequest);
        }
        throw new UnsupportedTransactionTypeException(transactionType);
    }
}
