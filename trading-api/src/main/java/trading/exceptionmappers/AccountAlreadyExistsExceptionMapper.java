package trading.exceptionmappers;

import trading.exception.AccountAlreadyExistsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import trading.api.response.ErrorResponse;


@Provider
public class AccountAlreadyExistsExceptionMapper implements
        ExceptionMapper<AccountAlreadyExistsException> {

    public Response toResponse(AccountAlreadyExistsException e) {
        String errorName = "ACCOUNT_ALREADY_OPEN";
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}