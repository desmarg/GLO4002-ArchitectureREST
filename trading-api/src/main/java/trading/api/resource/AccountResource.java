package trading.api.resource;

import trading.api.request.AccountPostRequestDTO;
import trading.api.response.AccountResponse;
import trading.api.response.ReportResponse;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.DateTime.DateTime;
import trading.domain.DateTime.DateTimeParser;
import trading.domain.Report.Report;
import trading.services.AccountService;
import trading.services.Services;
import trading.services.TransactionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountResource {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountResource(Services services) {
        this.accountService = services.getAccountService();
        this.transactionService = services.getTransactionService();
    }

    @GET
    @Path("/{accountNumber}/reports")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ReportResponse(@PathParam("accountNumber") String accountNumber,
                                   @QueryParam("type") String reportType,
                                   @QueryParam("date") String date) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        DateTime reportDate = DateTimeParser.createFromReportDate(date);
        Report report = this.transactionService.getReportFromDate(account, reportDate, reportType);
        return Response.status(Response.Status.OK).entity(new ReportResponse(report)).build();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        return Response.status(Response.Status.OK).entity(new AccountResponse(account)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostRequestDTO accountPostRequestDto) {
        AccountNumber accountNumber = this.accountService.save(accountPostRequestDto);
        return Response.status(Response.Status.CREATED).header(
                "Location",
                "accounts/" + accountNumber.getId()
        ).build();
    }
}
