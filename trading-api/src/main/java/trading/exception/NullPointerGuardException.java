package trading.exception;

import javax.ws.rs.core.Response.Status;
import java.lang.reflect.Field;

public class NullPointerGuardException extends MappedException {

    public NullPointerGuardException(Field field) {
        super(
                "INVALID_REQUEST",
                String.format("Field (%s) is missing", field.getName()),
                Status.BAD_REQUEST
        );
    }
}
