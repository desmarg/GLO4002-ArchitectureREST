package endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class Accounts {

   @GET
   @Path("/accounts")
   @Produces(MediaType.APPLICATION_JSON)
   public String available() {
      return "TO DO";
   }

}