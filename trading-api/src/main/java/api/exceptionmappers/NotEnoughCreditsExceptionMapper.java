package api.exceptionmappers;

import api.account.ErrorResponse;
import exception.NotEnoughCreditsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotEnoughCreditsExceptionMapper implements
        ExceptionMapper<NotEnoughCreditsException> {

    public Response toResponse(NotEnoughCreditsException e) {
        String errorName = "NOT_ENOUGH_CREDITS";
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}