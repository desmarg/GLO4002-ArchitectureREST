package trading.external.response.market;

import trading.domain.transaction.Transaction;
import trading.exception.MappedException;

import javax.ws.rs.core.Response;

public class MarketClosedException extends MappedException {

    public String transactionNumber;

    public MarketClosedException(Transaction transaction) {
        super("MARKET_CLOSED", "market '" + transaction.getMarket() + "' is closed", Response.Status.BAD_REQUEST);
        this.transactionNumber = transaction.getStringTransactionId();
    }
}
