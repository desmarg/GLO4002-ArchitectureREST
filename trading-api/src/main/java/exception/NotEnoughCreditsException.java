package exception;

public class NotEnoughCreditsException extends RuntimeException {
    public NotEnoughCreditsException(){
        super("not enough credits in wallet");
    }
}

