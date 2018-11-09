package trading.api.resource;

import application.market.MarketService;
import trading.api.request.TransactionPostRequestDTO;
import trading.api.response.TransactionResponse;
import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.domain.transaction.*;
import trading.exception.UnsupportedTransactionTypeException;
import trading.factory.TransactionResponseFactory;
import trading.services.AccountService;
import trading.services.Services;
import trading.services.StockService;
import trading.services.TransactionService;

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
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        if (TransactionType.fromString(transactionPostDto.type) == TransactionType.BUY) {
            transaction = this.transactionService.executeTransactionBuy(account, transactionPostDto);
        } else if (TransactionType.fromString(transactionPostDto.type) == TransactionType.SELL) {
            transaction = this.transactionService.executeTransactionSell(account, transactionPostDto);
        } else {
            throw new UnsupportedTransactionTypeException(transactionPostDto.type);
        }
        return Response.status(Response.Status.CREATED).header(
                "Location",
                "accounts/" + account.getLongAccountNumber()
                        + "/transactions/" + transaction.getStringTransactionId()
        ).build();
    }
}
