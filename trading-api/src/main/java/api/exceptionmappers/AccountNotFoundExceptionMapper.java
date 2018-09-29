package api.exceptionmappers;

import api.account.ErrorResponse;
import exception.AccountNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class AccountNotFoundExceptionMapper implements
        ExceptionMapper<AccountNotFoundException> {

    public Response toResponse(AccountNotFoundException e) {
        String errorName = "ACCOUNT_NOT_FOUND";
        String errorDescription = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
