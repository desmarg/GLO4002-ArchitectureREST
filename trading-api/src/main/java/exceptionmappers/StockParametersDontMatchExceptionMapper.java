package exceptionmappers;

import domain.transaction.TransactionNumber;
import exception.StockParametersDontMatchException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import api.response.ErrorResponse;


@Provider
public class StockParametersDontMatchExceptionMapper implements
        ExceptionMapper<StockParametersDontMatchException> {

    public Response toResponse(StockParametersDontMatchException e) {
        String errorName = "STOCK_PARAMETERS_DONT_MATCH";
        String errorDescription = e.getMessage();
        TransactionNumber transactionNumber = e.getTransactionNumber();

        ErrorResponse errorResponse = new ErrorResponse(errorName, errorDescription, transactionNumber);
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}