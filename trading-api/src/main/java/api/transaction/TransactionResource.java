package api.transaction;

import application.AccountService;
import application.TransactionService;
import domain.account.Account;
import domain.account.AccountNumber;
import domain.transaction.Transaction;
import domain.transaction.TransactionNumber;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/accounts/{accountNumber}/transactions")
public class TransactionResource {
    private TransactionService transactionService;
    private AccountService accountService;

    public TransactionResource(AccountService accountService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GET
    @Path("/{transactionNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionFromAll(@PathParam("accountNumber") long accountNumber, @PathParam("transactionNumber") String transactionNumberString) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        TransactionNumber transactionNumber = new TransactionNumber(UUID.fromString(transactionNumberString));
        Transaction transaction = this.transactionService.getTransactionFromAccount(account, transactionNumber);
        TransactionDto transactionDto = TransactionToTransactionGetDtoAssembler.createTransactionGetDto(transaction);
        return Response.status(Response.Status.OK).entity(transactionDto).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(@PathParam("accountNumber") long accountNumber, TransactionPostDto transactionPostDto) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        Transaction transaction = TransactionPostDtoToTransactionAssembler.createTransaction(transactionPostDto);
        TransactionService.makeTransaction(account, transaction);

        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber() + "/transactions/" + transaction.getStringTransactionId()).build();
    }
}
