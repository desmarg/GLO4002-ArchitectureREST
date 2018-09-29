package api.account;

import application.AccountService;
import domain.Account;
import domain.AccountNumber;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AccountResource {
    private AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/accounts/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber")long accountNumber) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));
        GetAccountDto getAccountDto
                = AccountMapper.INSTANCE.accountToGetAccountDto(account);
        return Response.status(Response.Status.CREATED).entity(getAccountDto).build();
    }

    @POST
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(PostAccountDto postAccountDto) {
        Account account = this.accountService.create(postAccountDto);
        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getAccountNumber()).build();
    }
}
