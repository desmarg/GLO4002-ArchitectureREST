package trading.domain.transaction;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;

public class TransactionSellAssembler {

    public static TransactionSell fromDTO(TransactionPostRequestDTO transactionPostRequestDTO,
                                          AccountNumber accountNumber,
                                          Credits stockPrice) {
        DateTime dateTime = new DateTime(transactionPostRequestDTO.date);
        Stock stock = new Stock(transactionPostRequestDTO.stock.market, transactionPostRequestDTO.stock.symbol);

        Long quantity = transactionPostRequestDTO.quantity;
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }
        TransactionNumber referredTransactionNumber = new TransactionNumber(transactionPostRequestDTO.transactionNumber);

        return new TransactionSell(quantity, dateTime, stock, stockPrice, referredTransactionNumber, accountNumber);
    }
}
