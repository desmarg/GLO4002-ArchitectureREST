package trading.exceptionmappers;

import trading.exception.NotEnoughCreditsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import trading.api.response.ErrorResponse;
import trading.domain.TransactionNumber;

@Provider
public class NotEnoughCreditsExceptionMapper implements
        ExceptionMapper<NotEnoughCreditsException> {

    public Response toResponse(NotEnoughCreditsException e) {
        String errorName = "NOT_ENOUGH_CREDITS";
        String errorDescription = e.getMessage();
        TransactionNumber transactionNumber = e.getTransactionNumber();

        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionNumber);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}