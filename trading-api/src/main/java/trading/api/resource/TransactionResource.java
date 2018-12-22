package trading.api.resource;

import trading.api.request.TransactionPostRequestDTO;
import trading.api.response.TransactionResponseDTO;
import trading.api.response.TransactionResponseDTOAssembler;
import trading.domain.transaction.Transaction;
import trading.domain.transaction.TransactionID;
import trading.domain.transaction.TransactionType;
import trading.domain.transaction.UnsupportedTransactionTypeException;
import trading.application.services.AppContext;
import trading.application.services.TransactionApplicationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/accounts/{accountNumber}/transactions")
public class TransactionResource {
    private final TransactionApplicationService transactionApplicationService;

    public TransactionResource(AppContext appContext) {
        this.transactionApplicationService = appContext.getTransactionApplicationService();
    }

    @GET
    @Path("/{transactionNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@PathParam("accountNumber") String accountNumber, @PathParam(
            "transactionNumber") String transactionIDAsString) {
        TransactionID transactionID = new TransactionID(transactionIDAsString);
        Transaction transaction = this.transactionApplicationService.getTransaction(transactionID);
        TransactionResponseDTO transactionResponseDTO =
                TransactionResponseDTOAssembler.createTransactionResponse(transaction);

        return Response.status(Response.Status.OK).entity(transactionResponseDTO).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTransaction(@PathParam("accountNumber") String accountNumber,
                                    TransactionPostRequestDTO transactionPostDto) {
        Transaction transaction;
        if (TransactionType.fromString(transactionPostDto.type) == TransactionType.BUY) {
            transaction = this.transactionApplicationService.executeTransactionBuy(accountNumber,
                    transactionPostDto);
        } else if (TransactionType.fromString(transactionPostDto.type) == TransactionType.SELL) {
            transaction = this.transactionApplicationService.executeTransactionSell(accountNumber,
                    transactionPostDto);
        } else {
            throw new UnsupportedTransactionTypeException(transactionPostDto.type);
        }
        return Response.status(Response.Status.CREATED).location(URI.create("accounts/" + accountNumber
                + "/transactions/" + transaction.getStringTransactionId())).build();
    }
}
