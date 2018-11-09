package trading.api.resource;

import trading.api.request.AccountPostRequestDTO;
import trading.api.response.AccountResponse;
import trading.domain.Account.Account;
import trading.domain.Account.AccountNumber;
import trading.services.AccountService;
import trading.services.Services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountResource {
    private AccountService accountService;

    public AccountResource(Services services) {
        this.accountService = services.getAccountService();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        return Response.status(Response.Status.OK).entity(new AccountResponse(account)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostRequestDTO accountPostRequestDto) {
        Account account = this.accountService.save(accountPostRequestDto);
        return Response.status(Response.Status.CREATED).header(
                "Location",
                "accounts/" + account.getLongAccountNumber()
        ).build();
    }
}
