package exception;

public class MarketClosedException extends RuntimeException {
    public MarketClosedException() {
        super("market is closed");
    }
}