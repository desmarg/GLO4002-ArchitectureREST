package trading.api.resource;

import trading.api.request.AccountPostRequestDTO;
import trading.api.response.AccountResponseDTO;
import trading.application.services.AccountApplicationService;
import trading.domain.account.Account;
import trading.domain.account.AccountAssembler;
import trading.domain.account.AccountNumber;
import trading.persistence.BasicForexRates;
import trading.application.services.AppContext;
import trading.application.services.TransactionApplicationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/accounts")
public class AccountResource {
    private final AccountApplicationService accountApplicationService;
    private final TransactionApplicationService transactionApplicationService;

    public AccountResource(AppContext appContext) {
        this.accountApplicationService = appContext.getAccountApplicationService();
        this.transactionApplicationService = appContext.getTransactionApplicationService();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        Account account = this.accountApplicationService.findByAccountNumber(accountNumber);
        AccountResponseDTO accountResponseDTO = AccountAssembler.toAccountResponseDTO(account, new BasicForexRates());
        return Response.status(Response.Status.OK).entity(accountResponseDTO).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostRequestDTO accountPostRequestDto) {
        AccountNumber accountNumber = this.accountApplicationService.createAccount(accountPostRequestDto);
        return Response.status(Response.Status.CREATED).location(URI.create("accounts/" + accountNumber.getString())).build();
    }
}
