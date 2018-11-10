package trading.domain.transaction;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.services.StockService;

public class TransactionBuyAssembler {

    public static TransactionBuy fromDTO(TransactionPostRequestDTO transactionPostRequestDTO,
                                         AccountNumber accountNumber,
                                         StockService stockService) {
        DateTime dateTime = DateTime.fromInstant(transactionPostRequestDTO.date);
        Stock stock = transactionPostRequestDTO.stock;

        Long quantity = transactionPostRequestDTO.quantity;
        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }

        Credits stockPrice = stockService.getStockPrice(stock, dateTime);

        return new TransactionBuy(quantity, dateTime, stock, stockPrice, accountNumber);
    }
}
