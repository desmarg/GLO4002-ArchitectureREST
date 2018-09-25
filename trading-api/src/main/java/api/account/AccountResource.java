package api.account;

import application.AccountService;
import domain.Account;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistence.AccountAlreadyExistsException;
import persistence.AccountNotFoundByAccountNumberException;

@Path("/")
public class AccountResource {
   private AccountService accountService;

   public AccountResource(AccountService accountService) {
      this.accountService = accountService;
   }

   @GET
   @Path("/accounts/{accountNumber}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAccountByAccountNumber(@PathParam("accountNumber")Long accountNumber) {
      try {
         Account account = accountService.findByAccountNumber(accountNumber);
         AccountInformationDto accountInformationDto = AccountMapper.INSTANCE
                 .accountToAccountInformationDto(account);

         return Response.status(Response.Status.CREATED).entity(accountInformationDto).build();
      } catch (AccountNotFoundByAccountNumberException e) {
         return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e)).build();
      }
   }

   @POST
   @Path("/accounts")
   @Produces(MediaType.APPLICATION_JSON)
   public Response createAccount(AccountCreatorDto accountCreatorDto) {
      try {
         accountService.checkIfAccountExists(accountCreatorDto.getInvestorId());
         Long accountNumber = accountService.create(AccountMapper.INSTANCE
                 .accountCreatorDtoToAccount(accountCreatorDto));
         return Response.status(Response.Status.CREATED).header("Location", "accounts/"
                 + accountNumber).build();
      } catch (AccountAlreadyExistsException e) {
         return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e)).build();

      } catch (InvalidCreditsAmountException e) {
         return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e)).build();

      } catch (Exception e) {
          return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e)).build();
      }
   }
}