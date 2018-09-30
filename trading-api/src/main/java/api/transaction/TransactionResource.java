package api.transaction;

import application.AccountService;
import application.TransactionService;
import domain.account.Account;
import domain.account.AccountNumber;
import domain.transaction.Transaction;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

//    @GET
//    @Path("/accounts/{accountNumber}/transactions/{transactionNumber}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getTransaction(@PathParam("accountNumber") long accountNumber, @PathParam("transactionNumber") TransactionNumber transactionNumber) {
//        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
//        Transaction transaction = transactionService.getTransaction(account, transactionNumber);
//        TransactionGetDto transactionGetDto = TransactionMapper.INSTANCE.transactionToGetBuyTransactionDto(transaction);
//        return Response.status(Response.Status.CREATED).entity(transactionGetDto).build();
//    }

    @POST
    @Path("/accounts/{accountNumber}/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(@PathParam("accountNumber") long accountNumber, TransactionPostDto transactionPostDto) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        Transaction transaction = TransactionPostDtoToTransactionAssembler.createTransaction(transactionPostDto);
        TransactionService.makeTransaction(account, transaction);

        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber() + "/transactions/" + transaction.getStringTransactionId()).build();
    }
}
