package api.account;

import application.AccountService;
import domain.Account;
import persistence.AccountAlreadyExistsException;
import persistence.AccountNotFoundByAccountNumberException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;

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
      try{
         Account account = accountService.findByAccountNumber(accountNumber);
         AccountInformationDTO accountInformationDTO = AccountMapper.INSTANCE
                 .accountToAccountInformationDTO(account);

         return Response.status(Response.Status.CREATED).entity(accountInformationDTO).build();
      }
      catch(AccountNotFoundByAccountNumberException e){
         String errorName = "ACCOUNT_NOT_FOUND";
         String errorDescription = MessageFormat.format("account with number {0} not found",
                 accountNumber);
         return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(errorName,
                 errorDescription)).build();
      }
   }

   @POST
   @Path("/accounts")
   @Produces(MediaType.APPLICATION_JSON)
   public Response createAccount(AccountCreatorDTO accountCreatorDTO) {
      try {
         Long accountNumber = accountService.create(AccountMapper.INSTANCE
                 .accountCreatorDTOToAccount(accountCreatorDTO));
         return Response.status(Response.Status.CREATED).header("Location", "accounts/" +
                 accountNumber).build();
      } catch (AccountAlreadyExistsException e) {
         String errorName = "ACCOUNT_ALREADY_OPEN";
         String errorDescription = MessageFormat.format("account already open for investor {0}",
                 accountCreatorDTO.getInvestorId());
         return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(errorName,
                 errorDescription)).build();
      }
      catch (InvalidCreditsAmountException e) {
         String errorName = "INVALID_AMOUNT";
         String errorDescription = "credit amount cannot be lower than or equal to zero";
         return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(errorName,
                 errorDescription)).build();
      }
   }
}