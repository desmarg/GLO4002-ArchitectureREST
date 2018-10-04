package exceptionmappers;

import exception.InvalidCreditsAmountException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import api.response.ErrorResponse;

@Provider
public class InvalidCreditsAmountExceptionMapper implements
        ExceptionMapper<InvalidCreditsAmountException> {

    public Response toResponse(InvalidCreditsAmountException e) {
        String errorName = "INVALID_AMOUNT";
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}