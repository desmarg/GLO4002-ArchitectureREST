package trading.api.resource;

import trading.api.request.AccountPostRequestDTO;
import trading.api.response.AccountResponseDTO;
import trading.api.response.ReportResponseDTO;
import trading.domain.account.Account;
import trading.domain.account.AccountNumber;
import trading.domain.report.Report;
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
    public Response reportResponse(@PathParam("accountNumber") String accountNumber, @QueryParam(
            "type") String reportType, @QueryParam("date") String date) {
        Report report = this.transactionService.getReportFromDate(accountNumber, date, reportType);
        return Response.status(Response.Status.OK).entity(new ReportResponseDTO(report)).build();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        return Response.status(Response.Status.OK).entity(new AccountResponseDTO(account)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostRequestDTO accountPostRequestDto) {
        AccountNumber accountNumber = this.accountService.save(accountPostRequestDto);
        return Response.status(Response.Status.CREATED).header("Location",
                "accounts/" + accountNumber.getString()).build();
    }
}
