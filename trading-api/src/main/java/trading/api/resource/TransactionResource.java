package trading.api.resource;

import trading.api.request.TransactionPostRequestDTO;
import trading.api.response.TransactionResponse;
import trading.api.response.TransactionResponseFactory;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.domain.transaction.*;
import trading.exception.UnsupportedTransactionTypeException;
import trading.services.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/accounts/{accountNumber}/transactions")
public class TransactionResource {
    private TransactionService transactionService;
    private AccountService accountService;
    private StockService stockService;
    private MarketService marketService;

    public TransactionResource(Services services) {
        this.transactionService = services.getTransactionService();
        this.accountService = services.getAccountService();
        this.stockService = services.getStockService();
        this.marketService = services.getMarketService();
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
        if (!transaction.getAccountNumber().equals(accountNumber)) {
            throw new RuntimeException("This account does not have this transaction");
        }
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
        //this.marketService.assertMarketOpenAtHour(transactionPostDto.stock.getMarket());
        Transaction transaction;
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        if (TransactionType.fromString(transactionPostDto.type) == TransactionType.BUY) {
            TransactionBuy transactionBuy = TransactionBuyAssembler.fromDTO(transactionPostDto, account.getAccountNumber(), this.stockService);
            this.transactionService.executeTransactionBuy(account, transactionBuy);
            transaction = transactionBuy;
        } else if (TransactionType.fromString(transactionPostDto.type) == TransactionType.SELL) {
            TransactionSell transactionSell = TransactionSellAssembler.fromDTO(transactionPostDto, account.getAccountNumber(), this.stockService);
            this.transactionService.executeTransactionSell(account, transactionSell);
            transaction = transactionSell;
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
