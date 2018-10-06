package trading.api.resource;

import trading.api.request.AccountPostRequest;
import trading.api.response.AccountResponse;
import trading.domain.Account;
import trading.domain.AccountNumber;
import trading.services.AccountService;
import trading.services.Services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostRequest accountPostDto) {
        Account account = Account.fromRequest(accountPostDto, this.accountService.nextAccountNumber());
        this.accountService.saveAccount(account);
        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber()).build();
    }
}