package trading.domain.transaction;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.exception.InvalidQuantityException;
import trading.services.StockService;

public class TransactionSellAssembler {

    public static TransactionSell fromDTO(TransactionPostRequestDTO transactionPostRequestDTO,
                                          StockService stockService) {
        DateTime dateTime = transactionPostRequestDTO.date;
        Stock stock = transactionPostRequestDTO.stock;

        Long quantity = transactionPostRequestDTO.quantity;
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }
        TransactionNumber referredTransactionNumber = transactionPostRequestDTO.transactionNumber;
        Credits stockPrice = stockService.getStockPrice(stock, dateTime);

        return new TransactionSell(quantity, dateTime, stock, stockPrice, referredTransactionNumber);
    }
}
