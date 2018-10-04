package trading.exceptionmappers;

import trading.exception.UnsupportedTransactionTypeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import trading.api.response.ErrorResponse;

@Provider
public class UnsupportedTransactionTypeExceptionMapper implements ExceptionMapper<UnsupportedTransactionTypeException> {
    public Response toResponse(UnsupportedTransactionTypeException e) {
        String errorName = "UNSUPPORTED_TRANSACTION_TYPE";
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}