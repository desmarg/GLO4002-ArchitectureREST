package exception;

public class InvalidTimezoneException extends RuntimeException {
    public InvalidTimezoneException(String timezone) {
        super("Timezone '" + timezone + "' is invalid");
    }
}
