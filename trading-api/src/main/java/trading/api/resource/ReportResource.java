package trading.api.resource;

import trading.api.response.ReportResponse;
import trading.domain.*;
import trading.domain.transaction.Transaction;
import trading.services.AccountService;
import trading.services.Services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/accounts/{accountNumber}/reports")
public class ReportResource {
    private AccountService accountService;

    public ReportResource(Services services) {
        this.accountService = services.getAccountService();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReport(@PathParam("accountNumber") String accountNumber,
                              @QueryParam("type") String type,
                              @QueryParam("date") String date) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        DateTime reportDate = new DateTime(date, 23, 59, 59);
        ReportType reportType = ReportType.fromString(type);
        Report report = new Report(account, reportDate, reportType);
        return Response.status(Response.Status.OK).entity(new ReportResponse(report)).build();
    }


}
