package exceptionmappers;

import api.account.ErrorResponse;
import exception.MarketClosedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MarketClosedExceptionMapper implements ExceptionMapper<MarketClosedException> {
    public Response toResponse(MarketClosedException e) {
        String errorName = "MARKET_CLOSED";
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}