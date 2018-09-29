package exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String date) {
        super("Stock not found for the date " + date);
    }
}
