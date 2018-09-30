package api.account;

import application.AccountService;
import domain.account.Account;
import domain.account.AccountNumber;

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
    public Response getAccountByAccountNumber(@PathParam("accountNumber") long accountNumber) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));

        AccountGetDto accountGetDto = AccountToAccountGetDtoAssembler.makeGetAccountDto(account);
        return Response.status(Response.Status.OK).entity(accountGetDto).build();
    }

    @POST
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostDto accountPostDto) {
        Account account = AccountPostDtoToAccountAssembler.createAccount(accountPostDto, this.accountService.nextAccountNumber());
        this.accountService.saveAccount(account);
        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber()).build();
    }
}
