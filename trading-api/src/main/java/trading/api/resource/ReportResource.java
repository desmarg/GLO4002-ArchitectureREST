package trading.api.resource;

import trading.api.response.ReportResponseAssembler;
import trading.api.response.ReportResponseDTO;
import trading.domain.report.Report;
import trading.application.services.AppContext;
import trading.application.services.TransactionApplicationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts/{accountNumber}/reports")
public class ReportResource {
    private final TransactionApplicationService transactionApplicationService;

    public ReportResource(AppContext appContext) {
        this.transactionApplicationService = appContext.getTransactionApplicationService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReport(@PathParam("accountNumber") String accountNumber, @QueryParam(
            "type") String reportType, @QueryParam("date") String date) {
        Report report = this.transactionApplicationService.getAccountReportFromDate(accountNumber, date, reportType);
        ReportResponseDTO reportResponseDTO = ReportResponseAssembler.toReportResponseDTO(report);
        return Response.status(Response.Status.OK).entity(reportResponseDTO).build();
    }
}
