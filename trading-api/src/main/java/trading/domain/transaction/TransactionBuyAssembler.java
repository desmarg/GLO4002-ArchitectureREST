package trading.domain.transaction;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Credits;
import trading.domain.Stock;
import trading.domain.account.AccountNumber;
import trading.domain.datetime.DateTime;

public class TransactionBuyAssembler {

    public static TransactionBuy fromDTO(TransactionPostRequestDTO transactionPostRequestDTO,
                                         AccountNumber accountNumber,
                                         Credits stockPrice) {
        DateTime dateTime = new DateTime(transactionPostRequestDTO.date);
        Stock stock = new Stock(transactionPostRequestDTO.stock.market, transactionPostRequestDTO.stock.symbol);

        Long quantity = transactionPostRequestDTO.quantity;
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }

        return new TransactionBuy(quantity, dateTime, stock, stockPrice, accountNumber);
    }
}
