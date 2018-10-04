package api.views;

import domain.Account;
import domain.AccountNumber;
import domain.Transaction;
import domain.TransactionNumber;
import services.AccountService;
import services.Services;
import services.TransactionService;
import api.request.TransactionPostRequest;
import api.response.TransactionResponse;
import api.transaction.TransactionPostDtoToTransactionAssembler;
import api.transaction.TransactionToTransactionGetDtoAssembler;

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
        TransactionResponse transactionDto = TransactionToTransactionGetDtoAssembler.createTransactionGetDto(transaction);
        return Response.status(Response.Status.OK).entity(transactionDto).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(@PathParam("accountNumber") String accountNumber, TransactionPostRequest transactionPostDto) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        Transaction transaction = TransactionPostDtoToTransactionAssembler.createTransaction(transactionPostDto);
        TransactionService.makeTransaction(account, transaction);

        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber() + "/transactions/" + transaction.getStringTransactionId()).build();
    }
}
