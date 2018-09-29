package exception;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(String symbol, String market) {
        super("stock '" + symbol + ":" + market + "' not found");
    }
}