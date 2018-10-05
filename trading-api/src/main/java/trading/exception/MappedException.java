package trading.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.ws.rs.core.Response.Status;

// Explicitly ignore inherited exceptions attributes in mapped Json.
@JsonIgnoreProperties({"cause", "stackTrace", "message", "localizedMessage", "suppressed"})
public abstract class MappedException extends RuntimeException {

    protected String error;
    protected String description;
    protected Status status;

    public MappedException() {
    }

    public MappedException(String error, String description, Status status) {
        super(description);
        this.error = error;
        this.description = description;
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.status;
    }
}
