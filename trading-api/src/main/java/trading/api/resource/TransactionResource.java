package trading.api.resource;

import trading.api.request.TransactionPostRequestDTO;
import trading.api.response.TransactionResponse;
import trading.api.response.TransactionResponseFactory;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionType;
import trading.domain.transaction.UnsupportedTransactionTypeException;
import trading.services.AccountService;
import trading.services.Services;
import trading.services.TransactionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/accounts/{accountNumber}/transactions")
public class TransactionResource {
    private final TransactionService transactionService;
    private final AccountService accountService;

    public TransactionResource(Services services) {
        this.transactionService = services.getTransactionService();
        this.accountService = services.getAccountService();
    }

    @GET
    @Path("/{transactionNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(
            @PathParam("accountNumber") String accountNumber,
            @PathParam("transactionNumber") String transactionNumberString
    ) {
        TransactionNumber transactionNumber = new TransactionNumber(
                UUID.fromString(transactionNumberString)
        );

        Transaction transaction = this.transactionService.getTransaction(
                transactionNumber
        );
        TransactionResponse transactionDto =
                TransactionResponseFactory.createTransactionDto(transaction);

        return Response.status(Response.Status.OK).entity(transactionDto).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(
            @PathParam("accountNumber") String accountNumber,
            TransactionPostRequestDTO transactionPostDto
    ) {
        Transaction transaction;
        Account account = this.accountService.findByAccountNumber(Long.valueOf(accountNumber));
        if (TransactionType.fromString(transactionPostDto.type) == TransactionType.BUY) {
            transaction = this.transactionService.executeTransactionBuy(account, transactionPostDto);
        } else if (TransactionType.fromString(transactionPostDto.type) == TransactionType.SELL) {
            transaction = this.transactionService.executeTransactionSell(account, transactionPostDto);
        } else {
            throw new UnsupportedTransactionTypeException(transactionPostDto.type);
        }
        return Response.status(Response.Status.CREATED).header(
                "Location",
                "accounts/" + account.getId()
                        + "/transactions/" + transaction.getStringTransactionId()
        ).build();
    }
}
