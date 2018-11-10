package trading.domain.transaction;

import trading.api.request.TransactionPostRequestDTO;
import trading.domain.Account.AccountNumber;
import trading.domain.Credits.Credits;
import trading.domain.DateTime.DateTime;
import trading.domain.Stock;
import trading.services.StockService;

public class TransactionBuyAssembler {

    public static TransactionBuy fromDTO(final TransactionPostRequestDTO transactionPostRequestDTO,
                                         final AccountNumber accountNumber,
                                         final StockService stockService) {

        final DateTime dateTime = transactionPostRequestDTO.date;
        final Stock stock = transactionPostRequestDTO.stock;
        final Long quantity = transactionPostRequestDTO.quantity;

        if (quantity <= 0) {
            throw new InvalidQuantityException();
        }

        final Credits stockPrice = stockService.retrieveStockPrice(stock, dateTime);

        return new TransactionBuy(quantity, dateTime, stock, stockPrice, accountNumber);
    }
}
