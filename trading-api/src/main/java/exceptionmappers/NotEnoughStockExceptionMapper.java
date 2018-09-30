package exceptionmappers;

import api.account.ErrorResponse;
import exception.NotEnoughStockException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotEnoughStockExceptionMapper implements
        ExceptionMapper<NotEnoughStockException> {

    public Response toResponse(NotEnoughStockException e) {
        String errorName = "NOT_ENOUGH_STOCK";
        String errorDescription = e.getMessage();
        String transactionId = e.getTransactionNumber().getStringUUID();

        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionId);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}