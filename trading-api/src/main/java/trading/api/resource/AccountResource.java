package trading.api.resource;

import trading.api.request.AccountPostRequestDTO;
import trading.api.response.AccountResponseDTO;
import trading.domain.account.Account;
import trading.domain.account.AccountAssembler;
import trading.domain.account.AccountNumber;
import trading.persistence.BasicForexRates;
import trading.services.AccountService;
import trading.services.Services;
import trading.services.TransactionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountResource {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountResource(Services services) {
        this.accountService = services.getAccountService();
        this.transactionService = services.getTransactionService();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        Account account = this.accountService.findByAccountNumber(accountNumber);
        AccountResponseDTO accountResponseDTO = AccountAssembler.toAccountResponseDTO(account, new BasicForexRates());
        return Response.status(Response.Status.OK).entity(accountResponseDTO).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostRequestDTO accountPostRequestDto) {
        AccountNumber accountNumber = this.accountService.createAccount(accountPostRequestDto);
        return Response.status(Response.Status.CREATED).header("Location",
                "accounts/" + accountNumber.getString()).build();
    }
}
