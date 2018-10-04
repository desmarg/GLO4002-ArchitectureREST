package exceptionmappers;

import domain.transaction.TransactionNumber;
import exception.InvalidTransactionNumberException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import api.response.ErrorResponse;

@Provider
public class InvalidTransactionNumberMapper implements ExceptionMapper<InvalidTransactionNumberException> {
    public Response toResponse(InvalidTransactionNumberException e) {
        String errorName = "INVALID_TRANSACTION_NUMBER";
        String errorDescription = e.getMessage();
        TransactionNumber transactionNumber = e.getTransactionNumber();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionNumber);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}