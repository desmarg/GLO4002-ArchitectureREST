package exception;

public class MarketClosedException extends RuntimeException {
    public MarketClosedException(String market) {
        super("market '" + market + "' is closed");
    }
}