package exceptionmappers;

import exception.InvalidQuantityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import api.response.ErrorResponse;
import domain.TransactionNumber;

@Provider
public class InvalidQuantityExceptionMapper implements
        ExceptionMapper<InvalidQuantityException> {

    public Response toResponse(InvalidQuantityException e) {
        String errorName = "INVALID_QUANTITY";
        String errorDescription = e.getMessage();
        TransactionNumber transactionNumber = e.getTransactionNumber();

        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionNumber);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}