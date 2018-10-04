package api.views;

import application.AccountService;
import domain.account.Account;
import domain.account.AccountNumber;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import api.account.AccountPostDtoToAccountAssembler;
import api.account.AccountToAccountGetDtoAssembler;
import api.request.AccountPostRequest;
import api.response.AccountResponse;

@Path("/accounts")
public class AccountResource {
	public AccountResource() {
		
	}
	
    private AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        Account account = this.accountService.findByAccountNumber(new AccountNumber(accountNumber));

        AccountResponse accountGetDto = AccountToAccountGetDtoAssembler.makeGetAccountDto(account);
        return Response.status(Response.Status.OK).entity(accountGetDto).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountPostRequest accountPostDto) {
        Account account = AccountPostDtoToAccountAssembler.createAccount(accountPostDto, this.accountService.nextAccountNumber());
        this.accountService.saveAccount(account);
        return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                + account.getLongAccountNumber()).build();
    }
}
