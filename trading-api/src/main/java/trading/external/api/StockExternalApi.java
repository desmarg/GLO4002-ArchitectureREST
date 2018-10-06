package trading.external.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import trading.domain.Credits;
import trading.domain.DateTime;
import trading.domain.Stock;
import trading.domain.transaction.TransactionNumber;
import trading.exception.StockNotFoundException;
import trading.external.response.StockPriceResponse;
import trading.external.response.StockResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class StockExternalApi{

	public static Credits getStockPrice(Stock stock, DateTime date) {
        String url = "http://localhost:8080/stocks/" + stock.getMarket() + "/" + stock.getSymbol() ;
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        StockResponse stockDto = webResource.get(StockResponse.class);
        if (stockDto == null) {
            throw new StockNotFoundException(stock.getSymbol(), stock.getMarket(), new TransactionNumber(UUID.randomUUID()));
        }
        System.out.println("Did the request" + stockDto);
        Credits sameDayCredit = StockExternalApi.getPriceFromDate(stockDto, date);
        return sameDayCredit;
        
    }
	
	public static Credits getPriceFromDate(StockResponse stockDto, DateTime date) {
        for (StockPriceResponse priceInfo : stockDto.getPrices()) {
            DateTime dateTime = new DateTime(priceInfo.getDate());

            if (dateTime.isSameDay(date)) {
                return priceInfo.getPrice();
            }
        }
        throw new StockNotFoundException(stockDto.getSymbol(), stockDto.getMarket(), new TransactionNumber(UUID.randomUUID()));
    }
}
