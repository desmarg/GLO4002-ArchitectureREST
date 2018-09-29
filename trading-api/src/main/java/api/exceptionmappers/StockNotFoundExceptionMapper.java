package api.exceptionmappers;

import api.account.ErrorResponse;
import exception.StockNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class StockNotFoundExceptionMapper implements ExceptionMapper<StockNotFoundException> {
    public Response toResponse(StockNotFoundException e) {
        String errorName = "STOCK_NOT_FOUND";
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
