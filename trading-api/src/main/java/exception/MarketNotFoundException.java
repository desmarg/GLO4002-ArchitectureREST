package exception;

public class MarketNotFoundException extends RuntimeException {
    public MarketNotFoundException(String market) {
        super("market '" + market + "' not found");
    }
}
