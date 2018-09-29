package exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String symbol, String market) {
        super("stock '" + symbol + ":" + market + "' not found");
    }
}
