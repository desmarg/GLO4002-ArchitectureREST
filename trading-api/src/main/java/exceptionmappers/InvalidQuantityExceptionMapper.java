package exceptionmappers;

import api.account.ErrorResponse;
import exception.InvalidQuantityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidQuantityExceptionMapper implements
        ExceptionMapper<InvalidQuantityException> {

    public Response toResponse(InvalidQuantityException e) {
        String errorName = "INVALID_QUANTITY";
        String errorDescription = e.getMessage();
        String transactionId = e.getTransactionNumber().getStringUUID();

        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionId);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}