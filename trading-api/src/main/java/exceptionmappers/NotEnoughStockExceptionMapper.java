package exceptionmappers;

import exception.NotEnoughStockException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import api.response.ErrorResponse;
import domain.TransactionNumber;

@Provider
public class NotEnoughStockExceptionMapper implements
        ExceptionMapper<NotEnoughStockException> {

    public Response toResponse(NotEnoughStockException e) {
        String errorName = "NOT_ENOUGH_STOCK";
        String errorDescription = e.getMessage();
        TransactionNumber transactionId = e.getTransactionNumber();

        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionId);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}