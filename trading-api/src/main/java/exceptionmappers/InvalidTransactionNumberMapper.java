package exceptionmappers;

import api.account.ErrorResponse;
import exception.InvalidTransactionNumberException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTransactionNumberMapper implements ExceptionMapper<InvalidTransactionNumberException> {
    public Response toResponse(InvalidTransactionNumberException e) {
        String errorName = "INVALID_TRANSACTION_NUMBER";
        String errorDescription = e.getMessage();
        String transactionId = e.getTransactionNumber().getStringUUID();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionId);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}