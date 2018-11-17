package trading.api.resource;

import trading.api.request.TransactionPostRequestDTO;
import trading.api.response.TransactionResponse;
import trading.api.response.TransactionResponseFactory;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionNumber;
import trading.domain.transaction.TransactionType;
import trading.domain.transaction.UnsupportedTransactionTypeException;
import trading.services.Services;
import trading.services.TransactionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts/{accountNumber}/transactions")
public class TransactionResource {
    private final TransactionService transactionService;

    public TransactionResource(Services services) {
        this.transactionService = services.getTransactionService();
    }

    @GET
    @Path("/{transactionNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(
            @PathParam("accountNumber") String accountNumber,
            @PathParam("transactionNumber") String transactionNumberParam
    ) {
        TransactionNumber transactionNumber = new TransactionNumber(transactionNumberParam);
        Transaction transaction = this.transactionService.getTransaction(transactionNumber);
        TransactionResponse transactionResponse = TransactionResponseFactory.createTransactionResponse(transaction);

        return Response.status(Response.Status.OK).entity(transactionResponse).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(
            @PathParam("accountNumber") String accountNumber,
            TransactionPostRequestDTO transactionPostDto
    ) {
        Transaction transaction;
        if (TransactionType.fromString(transactionPostDto.type) == TransactionType.BUY) {
            transaction = this.transactionService.executeTransactionBuy(accountNumber, transactionPostDto);
        } else if (TransactionType.fromString(transactionPostDto.type) == TransactionType.SELL) {
            transaction = this.transactionService.executeTransactionSell(accountNumber, transactionPostDto);
        } else {
            throw new UnsupportedTransactionTypeException(transactionPostDto.type);
        }
        return Response.status(Response.Status.CREATED).header(
                "Location",
                "accounts/" + accountNumber + "/transactions/" + transaction.getStringTransactionId()
        ).build();
    }
}
