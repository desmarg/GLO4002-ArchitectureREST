package trading.api.resource;

import trading.api.request.TransactionPostRequest;
import trading.api.response.AccountResponse;
import trading.api.response.TransactionResponse;
import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.domain.ReportType;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;
import trading.exception.UnsupportedReportTypeException;
import trading.factory.TransactionFactory;
import trading.factory.TransactionResponseFactory;
import trading.services.AccountService;
import trading.services.Services;
import trading.services.TransactionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/accounts/{accountNumber}/reports")
public class ReportResource {
    private TransactionService transactionService;
    private AccountService accountService;

    public ReportResource(Services services) {
        this.transactionService = services.getTransactionService();
        this.accountService = services.getAccountService();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReport(@PathParam("accountNumber") String accountNumber,
                              @QueryParam("type") String reportType,
                              @QueryParam("date") String date) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        try {
            ReportType.valueOf(reportType);
        } catch (Exception e) {
            throw new UnsupportedReportTypeException(reportType);
        }

        return Response.status(Response.Status.OK).entity(new AccountResponse(account)).build();
    }


}
