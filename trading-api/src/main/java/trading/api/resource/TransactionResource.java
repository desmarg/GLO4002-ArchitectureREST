package trading.api.resource;

import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.domain.Transaction;
import trading.domain.TransactionNumber;
import trading.factory.TransactionResponseFactory;
import trading.factory.TransactionFactory;
import trading.services.AccountService;
import trading.services.Services;
import trading.services.TransactionService;
import trading.api.request.TransactionPostRequest;
import trading.api.response.TransactionResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/accounts/{accountNumber}/transactions")
public class TransactionResource {
    private TransactionService transactionService;
    private AccountService accountService;

    public TransactionResource(Services services) {
        this.transactionService = services.getTransactionService();
        this.accountService = services.getAccountService();
    }

    @GET
    @Path("/{transactionNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionFromAll(@PathParam("accountNumber") String accountNumber, @PathParam("transactionNumber") String transactionNumberString) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionNumber transactionNumber = new TransactionNumber(UUID.fromString(transactionNumberString));
        Transaction transaction = this.transactionService.getTransactionFromAccount(account, transactionNumber);
        TransactionResponse transactionDto = TransactionResponseFactory.createTransactionDto(transaction);
        return Response.status(Response.Status.OK).entity(transactionDto).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(@PathParam("accountNumber") String accountNumber, TransactionPostRequest transactionPostDto) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        Transaction transaction = TransactionFactory.create(transactionPostDto);
        TransactionService.makeTransaction(account, transaction);

        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber() + "/transactions/" + transaction.getStringTransactionId()).build();
    }
}
