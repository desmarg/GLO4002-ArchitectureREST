package trading.domain;

import trading.api.request.StockDTO;
import trading.domain.datetime.DateTime;

public interface StockRepository {
    Credits retrieveStockPrice(StockDTO stock, DateTime dateTime, boolean fromReport);

    Credits retrieveStockPrice(Stock stock, DateTime dateTime, boolean fromReport);
}
