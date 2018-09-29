package api.transaction;

import api.transaction.buyTransaction.BuyTransactionMapper;
import api.transaction.buyTransaction.PostBuyTransactionDto;
import application.AccountService;
import application.TransactionService;
import domain.account.Account;
import domain.account.AccountNumber;
import domain.transaction.Transaction;
import domain.transaction.TransactionId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TransactionResource {
    private TransactionService transactionService;
    private AccountService accountService;

    public TransactionResource(AccountService accountService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GET
    @Path("/accounts/{accountNumber}/transactions/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber") long accountNumber, @PathParam("transactionId") TransactionId transactionId) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        Transaction transaction = transactionService.getTransaction(account, transactionId);
        return Response.status(Response.Status.CREATED).entity(transaction).build();
    }

    @POST
    @Path("/accounts/{accountNumber}/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(@PathParam("accountNumber") long accountNumber, PostBuyTransactionDto postBuyTransactionDto) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        Transaction transaction = BuyTransactionMapper.INSTANCE.buyTransactionDtoToTransaction(postBuyTransactionDto);
        TransactionService.makeTransaction(account, transaction);

        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber() + "/transactions/" + transaction.getStringTransactionId()).build();
    }
}
